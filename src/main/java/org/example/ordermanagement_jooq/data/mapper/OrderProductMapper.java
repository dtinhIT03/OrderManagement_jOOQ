package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.OrderProduct;
import org.example.ordermanagement_jooq.data.request.OrderProductRequest;
import org.example.ordermanagement_jooq.data.response.OrderProductResponse;
import org.example.ordermanagement_jooq.data.response.ProductResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class OrderProductMapper {
    @Mapping(target = "id",ignore = true)
    public abstract OrderProduct toOrderProduct(OrderProductRequest request);

    @Mapping(target = "productResponse", ignore = true)
    public abstract OrderProductResponse toOrderProductResponse(OrderProduct orderProduct);

    @AfterMapping
    protected void afterMappingOrderProductResponse(@MappingTarget OrderProductResponse orderProductResponse,
                                          @Context ProductResponse productResponse){
        orderProductResponse.setProductResponse(productResponse);
    }

    public OrderProductResponse toOrderProductResponse(OrderProduct orderProduct, ProductResponse productResponse){
        OrderProductResponse response =  toOrderProductResponse(orderProduct);
        afterMappingOrderProductResponse(response,productResponse);
        return response;
    }
}
