package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.Product;
import generated_sources.tables.records.ProductRecord;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.repository.ProductRepository;
import org.example.ordermanagement_jooq.util.PageResponseUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Product save(Product product) {
        ProductRecord record = dslContext.newRecord(PRODUCT,product);
        record.store();
        return record.into(Product.class);
    }

    @Override
    public boolean deleteById(Long id) {
        int rowAffected = dslContext.delete(PRODUCT).where(PRODUCT.ID.eq(id)).execute();
        return rowAffected > 0;
    }

    @Override
    public Page<Product> searchProduct(List<FilterCondition> filters, Pageable pageable) {
        Condition condition = PageResponseUtils.buildCondition(filters);
        List<Product> products =dslContext.selectFrom(PRODUCT).where(condition)
                .limit(pageable.getPageSize()).offset(pageable.getOffset()).fetchInto(Product.class);
        long total =countProduct(condition);
        return new PageImpl(products,pageable,total);
    }

    @Override
    public long countProduct(Condition condition) {
        return dslContext.fetchCount(dslContext.selectFrom(PRODUCT).where(condition));
    }
}
