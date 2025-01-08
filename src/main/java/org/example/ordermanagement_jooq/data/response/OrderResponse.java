package org.example.ordermanagement_jooq.data.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.enums.StatusOrder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderResponse {
    Long id;
    LocalDateTime dateOrder;
    LocalDateTime dateDelivery;
    LocalDateTime dateRecieve;
    StatusOrder status;
    UserResponse userResponse;
    List<OrderProductResponse> items;
}
