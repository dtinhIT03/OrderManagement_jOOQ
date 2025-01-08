package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.OrderProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.repository.OrderProductRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static generated_sources.Tables.ORDER_PRODUCT;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class OrderProductRepositoryImpl implements OrderProductRepository {

    DSLContext dslContext;

    @Override
    public Void save(List<OrderProduct> request) {
        dslContext.batchInsert(request.stream()
                .map(orderProduct -> dslContext.newRecord(ORDER_PRODUCT,orderProduct)).toList()).execute();

        return null;
    }

    @Override
    public List<OrderProduct> getAllByOrderId(Long orderId) {

        return dslContext.selectFrom(ORDER_PRODUCT)
                .where(ORDER_PRODUCT.ORDER_ID.eq(orderId))
                .fetchInto(OrderProduct.class);
    }

    @Override
    public List<OrderProduct> findAllByOrderIds(List<Long> orderIds) {
        return dslContext.selectFrom(ORDER_PRODUCT)
                .where(ORDER_PRODUCT.ORDER_ID.in(orderIds))
                .fetchInto(OrderProduct.class);
    }
}
