package org.example.ordermanagement_jooq.data.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductRequest {
    private Long orderId;
    private Long productId;
    private BigDecimal totalPrice;
    private Long quantity;
}
