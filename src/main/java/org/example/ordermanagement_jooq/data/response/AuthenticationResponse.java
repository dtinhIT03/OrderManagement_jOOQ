package org.example.ordermanagement_jooq.data.response;

import lombok.Builder;

@Builder
public class AuthenticationResponse {
    String token;
    boolean authenticated;
}
