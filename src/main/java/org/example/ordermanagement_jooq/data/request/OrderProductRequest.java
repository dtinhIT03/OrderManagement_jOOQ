package org.example.ordermanagement_jooq.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductRequest {
    @NotBlank(message = "orderId must be not blank")
    private Long orderId;
    @NotBlank(message = "productId must be not blank")
    private Long productId;
    @NotBlank(message = "totalPrice must be not blank")
    private BigDecimal totalPrice;
    @NotBlank(message = "quantity must be not blank")
    private Long quantity;
}
