package org.example.ordermanagement_jooq.repository;

import generated_sources.tables.pojos.OrderProduct;

import java.util.List;

public interface OrderProductRepository {
    Void save(List<OrderProduct> request);

    List<OrderProduct> getAllByOrderId(Long orderId);
}
