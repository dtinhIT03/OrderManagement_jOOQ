package org.example.ordermanagement_jooq.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RefreshToken {
    @NotBlank(message = "token is not blank!")
    String token;
}
