package org.example.ordermanagement_jooq.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    @NotBlank(message = "fullname is not blank!")
    String fullname;

    @NotBlank(message = "fullname is not blank!")
    String mail;

    @NotBlank(message = "fullname is not blank!")
    String password;
}
