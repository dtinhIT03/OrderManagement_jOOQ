package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.Order;
import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.data.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target ="id", ignore = true)
    Order toOrder(OrderRequest request);

    OrderResponse toOrderResponse(Order order);

    //dùng phương thức default hoặc aftermapping để xử lý các loại map phức tap

}
