package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.Product;
import org.example.ordermanagement_jooq.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAllByListId(List<Long> id) {
        return null;
    }
}
