package org.example.ordermanagement_jooq.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.EnumPattern;
import org.example.ordermanagement_jooq.data.enums.StatusOrder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequest {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "dateOrder must be not null")
    LocalDateTime dateOrder;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateDelivery;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateRecieve;

    @EnumPattern(name = "status",regexp = "PENDING|PROCESSING|SHIPPED|DELIVERED|RECIEVED|CANCELED|FAILED|RETURNED")
    @NotNull(message = "status must be not null")
    StatusOrder status;

    @NotNull(message = "userId must be not null")
    Long userId;

    @NotNull(message = "items must be not null")
    List<OrderProductRequest> items;



}
