package org.example.ordermanagement_jooq.service.impl;

import org.example.ordermanagement_jooq.service.OrderProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Override
    public List<OrderProductService> findAllByOrderId(Long orderId) {
        return null;
    }
}
