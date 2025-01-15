package org.example.ordermanagement_jooq.repository;

import generated_sources.tables.pojos.Product;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.jooq.Condition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAllByListId(List<Long> id);
    Product save(Product product);

    boolean deleteById(Long id);

    Page<Product> searchProduct(List<FilterCondition> filters, Pageable pageable);

    long countProduct(Condition condition);
}
