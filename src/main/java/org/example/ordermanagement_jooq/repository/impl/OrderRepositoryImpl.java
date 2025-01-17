package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.Order;
import generated_sources.tables.records.OrderRecord;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.enums.StatusOrder;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.exception.ResourceNotFoundException;
import org.example.ordermanagement_jooq.repository.OrderRepository;
import org.example.ordermanagement_jooq.util.PageResponseUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.UpdateSetMoreStep;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static generated_sources.Tables.ORDER;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    DSLContext dslContext;

    @Override
    public Order save(Order request) {
//        dslContext.insertInto(ORDER)
//                .set(ORDER.DATE_ORDER,request.getDateOrder())
//                .set(ORDER.DATE_DELIVERY,request.getDateDelivery())
//                .set(ORDER.DATE_RECIEVE,request.getDateRecieve())
//                .set(ORDER.STATUS,request.getStatus())
//                .set(ORDER.USER_ID,request.getUserId()).execute();
        OrderRecord record = dslContext.newRecord(ORDER,request);
        record.store();
        return record.into(Order.class);
    }

    @Override
    public Optional<Order>  findById(Long id) {

        return dslContext.selectFrom(ORDER).where(ORDER.ID.equal(id)).fetchOptionalInto(Order.class);
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

    @Override
    public Page<Order> searchOrders(List<FilterCondition> filterConditions, Pageable pageable) {
        Condition condition = PageResponseUtils.buildCondition(filterConditions);

        List<Order> orders = dslContext.selectFrom(ORDER)
                .where(condition)
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetchInto(Order.class);

        long totalCount = dslContext.selectCount().from(ORDER).where(condition)
                .fetchOne(0,long.class);//dùng khi ban biết chắc chắn truy vấn trả về 1 hàng duy nhất;
        return new PageImpl<>(orders,pageable,totalCount);
    }

    @Override
    public boolean exitsById(Long id) {
        return dslContext.fetchExists(dslContext.selectFrom(ORDER).where(ORDER.ID.eq(id)));
    }

    @Override
    public boolean delete(Long id) {
        int rowAffected =  dslContext.deleteFrom(ORDER).where(ORDER.ID.eq(id)).execute();
        return rowAffected >0;
    }

    @Override
    public Order update(Long id, StatusOrder status) {

        int updated = dslContext.update(ORDER)
                .set(ORDER.STATUS, status.toString())
                .set(
                        switch (status) {
                            case RECIEVED -> ORDER.DATE_RECIEVE;
                            case DELIVERED -> ORDER.DATE_DELIVERY;
                            default -> throw new IllegalStateException("Unexpected value: " + status);
                        },
                        LocalDateTime.now()
                )
                .where(ORDER.ID.eq(id))
                .execute();

        // Kiểm tra xem có update thành công không
        if (updated == 0) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }

        // Trả về order đã được cập nhật
        return dslContext.selectFrom(ORDER)
                .where(ORDER.ID.eq(id))
                .fetchOneInto(Order.class);

    }




}
