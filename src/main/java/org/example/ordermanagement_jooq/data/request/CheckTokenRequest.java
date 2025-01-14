package org.example.ordermanagement_jooq.data.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckTokenRequest {
    String token;
}
