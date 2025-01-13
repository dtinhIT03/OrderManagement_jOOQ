package org.example.ordermanagement_jooq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class CustomJwtDecoder implements JwtDecoder {
    //khai báo nimbus dùng để giải mã, xác thực token
    NimbusJwtDecoder nimbusJwtDecoder;
    @Override
    public Jwt decode(String token) throws JwtException {
        return null;
    }
}
