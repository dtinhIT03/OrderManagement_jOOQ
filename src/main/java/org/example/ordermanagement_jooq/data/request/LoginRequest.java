package org.example.ordermanagement_jooq.data.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @Email
    String mail;
    @NotBlank(message = "password is not blank!")
    String password;
}
