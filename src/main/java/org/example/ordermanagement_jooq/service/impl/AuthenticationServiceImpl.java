package org.example.ordermanagement_jooq.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import generated_sources.tables.pojos.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.request.CheckTokenRequest;
import org.example.ordermanagement_jooq.data.response.AuthenticationResponse;
import org.example.ordermanagement_jooq.data.response.CheckTokenResponse;
import org.example.ordermanagement_jooq.repository.UserRepository;
import org.example.ordermanagement_jooq.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${jwt.signerKey}")
    private String signerKey;
    @Value("${jwt.valid_duration}")
    private long validDuration;
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
    public CheckTokenResponse checkToken(CheckTokenRequest request) {
        return null;
    }
    //hàm verify token
    SignedJWT verifyToken(String token, boolean)
}
