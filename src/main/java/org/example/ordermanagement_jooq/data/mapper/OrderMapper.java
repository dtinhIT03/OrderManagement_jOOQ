package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.Order;
import generated_sources.tables.pojos.OrderProduct;
import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.data.response.OrderProductResponse;
import org.example.ordermanagement_jooq.data.response.OrderResponse;
import org.example.ordermanagement_jooq.data.response.UserResponse;
import org.mapstruct.*;


import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Mapping(target ="id", ignore = true)
    public abstract Order toOrder(OrderRequest request);

    @Mapping(target = "userResponse", ignore = true)
    @Mapping(target = "items", ignore = true)
    public abstract  OrderResponse toOrderResponse(Order order);

    //dùng phương thức default hoặc aftermapping để xử lý các loại map phức tap
    @AfterMapping
    protected void afterMapping(@MappingTarget OrderResponse orderResponse,
                                @Context UserResponse userResponse,
                                @Context List<OrderProductResponse> orderProductResponses){

        orderResponse.setUserResponse(userResponse);
        orderResponse.setItems(orderProductResponses);
    }

    public OrderResponse toOrderResponse(Order order,UserResponse userResponse,List<OrderProductResponse> orderProductResponses){
        OrderResponse response =  toOrderResponse(order);
        afterMapping(response,userResponse,orderProductResponses);
        return response;
    }

    public List<OrderResponse> toListOrderResponse( List<Order> orders,
                                                            @Context Map<Long,UserResponse> userResponseMap,
                                                             @Context List<OrderProduct> orderProducts,
                                                            @Context Map<Long,OrderProductResponse> orderProductResponseMap){
        return orders.stream().map(order -> {
            UserResponse userResponse = userResponseMap.get(order.getUserId());
            List<OrderProductResponse> orderProductResponses = orderProducts.stream().filter(op -> op.getOrderId().equals(order.getId()))
                    .map(op -> orderProductResponseMap.get(op.getId())).toList();

            return toOrderResponse(order,userResponse,orderProductResponses);
        }).toList();

    }

}
