package org.example.ordermanagement_jooq.data.request;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    String mail;
    String password;
}
