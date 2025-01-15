package org.example.ordermanagement_jooq.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckTokenRequest {
    @NotBlank(message = "token is not blank!")
    String token;
}
