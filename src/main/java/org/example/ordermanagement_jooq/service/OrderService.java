package org.example.ordermanagement_jooq.service;

import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.data.response.OrderResponse;

public interface OrderService {
    Void save(OrderRequest request);
    OrderResponse getById(Long id);
}
