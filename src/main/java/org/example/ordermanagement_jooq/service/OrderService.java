package org.example.ordermanagement_jooq.service;

import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.data.response.OrderResponse;
import org.example.ordermanagement_jooq.data.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Void save(OrderRequest request);
    OrderResponse getById(Long id);
//    Page<OrderResponse> getOrders(Pageable pageable);

    PageResponse<OrderResponse> getPageOrders(Pageable pageable);

    PageResponse<OrderResponse> searchOrders(List<FilterCondition> filterConditions, Pageable pageable);
}
