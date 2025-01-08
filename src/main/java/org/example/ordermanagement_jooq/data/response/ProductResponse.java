package org.example.ordermanagement_jooq.data.response;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    BigDecimal sellingPrice;
    String name;
}
