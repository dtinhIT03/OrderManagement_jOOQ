package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.Order;
import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Void save(Order request) {

        return null;
    }

    @Override
    public Order findById(Long id) {
        return null;
    }
}
