package org.example.ordermanagement_jooq.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import generated_sources.tables.pojos.OrderProduct;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.EnumPattern;
import org.example.ordermanagement_jooq.data.enums.StatusOrder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderRequest {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateOrder;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateDelivery;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateRecieve;

    @EnumPattern(name = "status",regexp = "PENDING|PROCESSING|SHIPPED|DELIVERED|RECIEVED|CANCELED|FAILED|RETURNED")
    StatusOrder status;

    Long userId;

    List<OrderProduct> items;


}
