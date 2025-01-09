package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.Order;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.repository.OrderRepository;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static generated_sources.Tables.ORDER;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    DSLContext dslContext;

    @Override
    public Void save(Order request) {
        dslContext.insertInto(ORDER)
                .set(ORDER.DATE_ORDER,request.getDateOrder())
                .set(ORDER.DATE_DELIVERY,request.getDateDelivery())
                .set(ORDER.DATE_RECIEVE,request.getDateRecieve())
                .set(ORDER.STATUS,request.getStatus())
                .set(ORDER.USER_ID,request.getUserId()).execute();
        return null;
    }

    @Override
    public Order findById(Long id) {

        return dslContext.selectFrom(ORDER).where(ORDER.ID.equal(id)).fetchOne().into(Order.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Order> getOrders(Pageable pageable) {
        List<Order> orders = dslContext.selectFrom(ORDER)
                .limit(pageable.getPageSize()).offset(pageable.getOffset()).fetchInto(Order.class);
        long totalCount = countOrder();
        return new PageImpl<>(orders,pageable,totalCount);
    }

    @Override
    public long countOrder() {
        return dslContext.fetchCount(dslContext.select().from(ORDER));
    }

    @Override
    public List<Order> getPageOrders(Pageable pageable) {
        return null;
    }


}
