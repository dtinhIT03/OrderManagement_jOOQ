package org.example.ordermanagement_jooq.service;

import com.nimbusds.jose.JOSEException;
import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.request.CheckTokenRequest;
import org.example.ordermanagement_jooq.data.request.LoginRequest;
import org.example.ordermanagement_jooq.data.request.RefreshToken;
import org.example.ordermanagement_jooq.data.response.AuthenticationResponse;
import org.example.ordermanagement_jooq.data.response.CheckTokenResponse;

import java.text.ParseException;

public interface AuthenticationService {
   //authenticate để tạo token
    AuthenticationResponse authenticate(LoginRequest request);
    // kiểm tra token -> lấy ra
    CheckTokenResponse checkToken(CheckTokenRequest request) throws ParseException, JOSEException;
    // refresh token
    AuthenticationResponse refreshToken(RefreshToken refreshToken) throws ParseException, JOSEException;
    //register
    boolean register(AuthenticationRequest request);
}
