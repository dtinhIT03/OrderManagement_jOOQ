package org.example.ordermanagement_jooq.service;

import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.request.CheckTokenRequest;
import org.example.ordermanagement_jooq.data.response.AuthenticationResponse;
import org.example.ordermanagement_jooq.data.response.CheckTokenResponse;

public interface AuthenticationService {
   //authenticate để tạo token
    AuthenticationResponse authenticate(AuthenticationRequest request);
    // kiểm tra token -> lấy ra
    CheckTokenResponse checkToken(CheckTokenRequest request);
}
