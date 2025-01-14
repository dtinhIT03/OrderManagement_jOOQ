package org.example.ordermanagement_jooq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private  CustomJwtDecoder customJwtDecoder;
    private final String PUBLIC_ENDPOINTS[] ={"/**"};
    /* SecurityFilterChain là 1 chuỗi các bộ lọc bảo mật đc áp dụng cho các request,
        httpSecurity là 1 API của Spring Security cho bạn cách custom cách bảo vệ(xác thực , phân quyền)
    *  */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /* dùng để kiểm soát quyền truy cập các endpoint bằng Role,Authority */
        httpSecurity.authorizeHttpRequests(auth ->
                auth.requestMatchers(PUBLIC_ENDPOINTS).permitAll().anyRequest().authenticated());

        /* cấu hình ứng dụng như 1 resource server, bảo vệ các endpoint bằng cách xác thực Jwt tokens,
        *   jwtAuthenconverter dùng để chuyển 1 jwt thành 1 đối tượng authentication
        *  */
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(request ->
                request.decoder(customJwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
                ).authenticationEntryPoint(new JwtAuthenticationEntryPoint())

        );
        return httpSecurity.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        //thiết lập "" nghĩa là quyền sẽ ko cần tiền tố phía trước, sau đó set vào jwtAuthenticationConverter
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;


    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
