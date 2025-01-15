package org.example.ordermanagement_jooq.service;

import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.data.request.ProductRequest;
import org.example.ordermanagement_jooq.data.response.PageResponse;
import org.example.ordermanagement_jooq.data.response.ProductResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponse getById(Long id);

    ProductResponse add(ProductRequest request);
    boolean delete(Long id);
    PageResponse<ProductResponse> search(List<FilterCondition> filters, Pageable pageable);
}
