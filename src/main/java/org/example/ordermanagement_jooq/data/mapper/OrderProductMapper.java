package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.OrderProduct;
import org.example.ordermanagement_jooq.data.request.OrderProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
    @Mapping(target = "id",ignore = true)
    OrderProduct toOrderProduct(OrderProductRequest request);
}
