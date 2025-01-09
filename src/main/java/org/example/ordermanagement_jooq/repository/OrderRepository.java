package org.example.ordermanagement_jooq.repository;

import generated_sources.tables.pojos.Order;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository {
    Void save(Order request);
    Order findById(Long id);
    Page<Order> getOrders(Pageable pageable);
    long countOrder();
    List<Order> getPageOrders(Pageable pageable);

    Page<Order> searchOrders(List<FilterCondition> filterConditions, Pageable pageable);
}
