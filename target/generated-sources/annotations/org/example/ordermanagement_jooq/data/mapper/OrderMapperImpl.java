package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.Order;
import javax.annotation.processing.Generated;
import org.example.ordermanagement_jooq.data.enums.StatusOrder;
import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.data.response.OrderResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-15T10:03:17+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl extends OrderMapper {

    @Override
    public Order toOrder(OrderRequest request) {
        if ( request == null ) {
            return null;
        }

        Order order = new Order();

        order.setDateOrder( request.getDateOrder() );
        order.setDateDelivery( request.getDateDelivery() );
        order.setDateRecieve( request.getDateRecieve() );
        order.setUserId( request.getUserId() );
        if ( request.getStatus() != null ) {
            order.setStatus( request.getStatus().name() );
        }

        return order;
    }

    @Override
    public OrderResponse toOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.id( order.getId() );
        orderResponse.dateOrder( order.getDateOrder() );
        orderResponse.dateDelivery( order.getDateDelivery() );
        orderResponse.dateRecieve( order.getDateRecieve() );
        if ( order.getStatus() != null ) {
            orderResponse.status( Enum.valueOf( StatusOrder.class, order.getStatus() ) );
        }

        return orderResponse.build();
    }
}
