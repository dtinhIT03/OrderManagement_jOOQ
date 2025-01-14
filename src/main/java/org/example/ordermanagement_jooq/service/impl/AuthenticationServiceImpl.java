package org.example.ordermanagement_jooq.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import generated_sources.tables.pojos.BlackListToken;
import generated_sources.tables.pojos.User;
import lombok.RequiredArgsConstructor;
import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.request.CheckTokenRequest;
import org.example.ordermanagement_jooq.data.request.RefreshToken;
import org.example.ordermanagement_jooq.data.response.AuthenticationResponse;
import org.example.ordermanagement_jooq.data.response.CheckTokenResponse;
import org.example.ordermanagement_jooq.repository.BlackListTokenRepository;
import org.example.ordermanagement_jooq.repository.UserRepository;
import org.example.ordermanagement_jooq.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${jwt.singerKey}")
    private String signerKey;
    @Value("${jwt.valid_duration}")
    private long validDuration;
    @Value("${jwt.refreshable_token}")
    private long refreshable;

    private final BlackListTokenRepository blackListTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    String generateToken(User user){
        //phải có header, và claim -> kí nó
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS512)
                .type(JOSEObjectType.JWT)
                .contentType("JWT")
                .keyID("auth-key")
                .build();
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getFullname()) //định danh chính của người dùng
                .issuer("dtdev") //chỉ định người phát hành token
                .claim("scope",buildScope(user)) //scope là vai trò của người dùng là gì
                .issueTime(new Date()) //thời gian luc token đc tạo ra
                .expirationTime(new Date(Instant.now().plusSeconds(validDuration).toEpochMilli())) //thời gian token hết hạn
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload =new Payload(claimsSet.toJSONObject()); //là chứa các dữ liệu trong claimsSet, dưới dạng json
        JWSObject jwsObject = new JWSObject(header,payload);
        try{
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
        } catch (KeyLengthException e) {
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }
    String buildScope(User user){
        return "ROLE_USER";
    }
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //lấy user ra sau đó kiểm tra password vs password trong request
        User user = userRepository.findByMail(request.getMail());
        boolean authenticated = passwordEncoder.matches(request.getPassword(),user.getPassword());
        if(!authenticated){
            throw new RuntimeException();
        }
        return AuthenticationResponse.builder()
                .authenticated(authenticated)
                .token(generateToken(user))
                .build();
    }

    @Override
    public CheckTokenResponse checkToken(CheckTokenRequest request) throws ParseException, JOSEException {
        var token = request.getToken();
        boolean valid = true;
        try{
            verifyToken(token,false);
        }catch (RuntimeException e){
            valid = false;
        }
        return CheckTokenResponse.builder().valid(valid).build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshToken refreshToken) throws ParseException, JOSEException {
        SignedJWT signedJWT = verifyToken(refreshToken.getToken(),true);
        var jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        LocalDateTime expiryTimeLocalDate = expiryTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        BlackListToken blackListToken = new BlackListToken(jwtId,expiryTimeLocalDate);
        blackListTokenRepository.save(blackListToken);

        var mail = signedJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findByMail(mail);

        var token = generateToken(user);

        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    //hàm verify token
    SignedJWT verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException {
        //parse ra đối tượng signedJWT để có thể làm việc với payload, header
        SignedJWT signedJWT = SignedJWT.parse(token);
        //đc tạo ra để kiểm tra chữ ký của token
        JWSVerifier verifier = new MACVerifier(signerKey);

        boolean verified = signedJWT.verify(verifier);

        Date expiryTime = (isRefresh) ? new Date(signedJWT.getJWTClaimsSet()
                .getIssueTime().toInstant().
                plus(refreshable, ChronoUnit.SECONDS).toEpochMilli()) : signedJWT.getJWTClaimsSet().getExpirationTime();
        if(!(verified && expiryTime.after(new Date()))) throw new RuntimeException();
        return signedJWT;
    }
}
