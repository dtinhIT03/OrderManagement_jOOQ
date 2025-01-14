package org.example.ordermanagement_jooq.config;

import com.nimbusds.jose.JOSEException;
import org.example.ordermanagement_jooq.data.request.CheckTokenRequest;
import org.example.ordermanagement_jooq.data.response.CheckTokenResponse;
import org.example.ordermanagement_jooq.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Configuration
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.singerKey}")
    private String singerKey;

    //khai báo nimbus dùng để giải mã, xác thực token
    NimbusJwtDecoder nimbusJwtDecoder;
    AuthenticationService authenticationService;
    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            CheckTokenResponse response = authenticationService.checkToken(CheckTokenRequest.builder().token(token).build());
            if(!response.isValid()) throw new JwtException("Token invalid");
        } catch (ParseException  | JOSEException e) {
            throw new JwtException(e.getMessage());
        }

        if(Objects.isNull(nimbusJwtDecoder)){
            SecretKeySpec secretKeySpec = new SecretKeySpec(singerKey.getBytes(),"HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512).build();
        }
        return nimbusJwtDecoder.decode(token);
    }
}
