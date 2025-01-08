package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.repository.ProductRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static generated_sources.Tables.PRODUCT;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    DSLContext dslContext;

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAllByListId(List<Long> id) {
        return dslContext.selectFrom(PRODUCT).where(PRODUCT.ID.in(id)).fetchInto(Product.class);
    }
}
