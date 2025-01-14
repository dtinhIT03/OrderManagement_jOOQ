package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.OrderProduct;
import javax.annotation.processing.Generated;
import org.example.ordermanagement_jooq.data.request.OrderProductRequest;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-13T17:47:16+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class OrderProductMapperImpl implements OrderProductMapper {

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
}
