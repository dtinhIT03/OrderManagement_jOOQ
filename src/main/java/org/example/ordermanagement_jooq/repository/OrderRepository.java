package org.example.ordermanagement_jooq.repository;

import generated_sources.tables.pojos.Order;
import org.example.ordermanagement_jooq.data.enums.StatusOrder;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order request);
    Optional<Order> findById(Long id);
    Page<Order> getOrders(Pageable pageable);
    long countOrder();
    List<Order> getPageOrders(Pageable pageable);

    Page<Order> searchOrders(List<FilterCondition> filterConditions, Pageable pageable);

    boolean exitsById(Long id);

    boolean delete(Long id);

    Order update(Long id, StatusOrder status);
}
