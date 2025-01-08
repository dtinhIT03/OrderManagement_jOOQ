package org.example.ordermanagement_jooq.service;

import java.util.List;

public interface OrderProductService {
    List<OrderProductService> findAllByOrderId (Long orderId);
}
