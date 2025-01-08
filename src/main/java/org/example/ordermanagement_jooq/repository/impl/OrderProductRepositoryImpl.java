package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.OrderProduct;
import org.example.ordermanagement_jooq.repository.OrderProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderProductRepositoryImpl implements OrderProductRepository {
    @Override
    public Void save(List<OrderProduct> request) {
        return null;
    }

    @Override
    public List<OrderProduct> getAllByOrderId(Long orderId) {
        return null;
    }
}
