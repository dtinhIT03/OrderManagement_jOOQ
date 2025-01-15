package org.example.ordermanagement_jooq.service;

import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.response.AuthenticationResponse;

public interface AuthService {
    boolean register(AuthenticationRequest request);

    AuthenticationResponse login(AuthenticationRequest request);

    String logout(String token);
}
