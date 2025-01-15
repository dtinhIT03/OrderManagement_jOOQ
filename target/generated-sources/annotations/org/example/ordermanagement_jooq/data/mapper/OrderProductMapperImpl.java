package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.OrderProduct;
import javax.annotation.processing.Generated;
import org.example.ordermanagement_jooq.data.request.OrderProductRequest;
import org.example.ordermanagement_jooq.data.response.OrderProductResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-15T10:03:17+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class OrderProductMapperImpl extends OrderProductMapper {

    @Override
    public OrderProduct toOrderProduct(OrderProductRequest request) {
        if ( request == null ) {
            return null;
        }

        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setOrderId( request.getOrderId() );
        orderProduct.setProductId( request.getProductId() );
        orderProduct.setTotalPrice( request.getTotalPrice() );
        orderProduct.setQuantity( request.getQuantity() );

        return orderProduct;
    }

    @Override
    public OrderProductResponse toOrderProductResponse(OrderProduct orderProduct) {
        if ( orderProduct == null ) {
            return null;
        }

        OrderProductResponse.OrderProductResponseBuilder orderProductResponse = OrderProductResponse.builder();

        orderProductResponse.quantity( orderProduct.getQuantity() );
        orderProductResponse.totalPrice( orderProduct.getTotalPrice() );

        return orderProductResponse.build();
    }
}
