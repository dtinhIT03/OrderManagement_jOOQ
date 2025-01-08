package org.example.ordermanagement_jooq.repository;

import generated_sources.tables.pojos.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAllByListId(List<Long> id);

}
