package org.example.ordermanagement_jooq.data.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckTokenResponse {
    boolean valid;
}
