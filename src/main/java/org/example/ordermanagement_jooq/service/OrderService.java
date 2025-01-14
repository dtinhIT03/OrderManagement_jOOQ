package org.example.ordermanagement_jooq.service;

import generated_sources.tables.pojos.Order;
import org.example.ordermanagement_jooq.data.enums.StatusOrder;
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

    PageResponse<OrderResponse> searchOrders(List<FilterCondition> filterConditions, Pageable pageable);

    void delete(Long id);

    Order changeStatusOrder(Long id, StatusOrder status);
}
