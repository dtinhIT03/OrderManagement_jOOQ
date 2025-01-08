package org.example.ordermanagement_jooq.repository;

import generated_sources.tables.pojos.Order;
import org.example.ordermanagement_jooq.data.request.OrderRequest;

public interface OrderRepository {
    Void save(Order request);
    Order findById(Long id);
}
