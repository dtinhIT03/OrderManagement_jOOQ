package org.example.ordermanagement_jooq.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderProductResponse {
    private ProductResponse productResponse;
    private Long quantity;
    private BigDecimal totalPrice;
}
