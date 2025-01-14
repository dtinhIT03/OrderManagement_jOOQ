package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.Order;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.repository.OrderRepository;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public Page<Order> searchOrders(List<FilterCondition> filterConditions, Pageable pageable) {
        Condition condition = buildCondition(filterConditions);

        List<Order> orders = dslContext.selectFrom(ORDER)
                .where(condition)
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetchInto(Order.class);

        long totalCount = dslContext.selectCount().from(ORDER).where(condition)
                .fetchOne(0,long.class);//dùng khi ban biết chắc chắn truy vấn trả về 1 hàng duy nhất;
        return new PageImpl<>(orders,pageable,totalCount);
    }

    private Condition buildCondition(List<FilterCondition> filters){
        if(filters.isEmpty() || filters == null){
            return DSL.noCondition(); // ko có câu truy vấn nào, where() sẽ đc loại bỏ
        }
        Condition condition = createCondition(filters.get(0));

        for(FilterCondition filter :filters){
            condition.and(createCondition(filter));
        }
        return condition;
    }
    private Condition createCondition(FilterCondition filter){
        //xác định các field cần tìm
        Field<Object> field = ORDER.field(filter.getField(),Object.class); //generic field
        if(field == null){
            throw new IllegalArgumentException("invalid field key:" +filter.getField());
        }
        Object castedValue = castToRequireType(filter.getField(),filter.getValue());
        switch (filter.getOperator()){
            case ":":
                return field.eq( castedValue); //equal condition
            case ">":
                return field.gt((Comparable<?>) castedValue);
            case "<":
                return field.lt((Comparable<?>) castedValue);
            case ">=":
                return field.ge((Comparable<?>) castedValue);
            case "<=":
                return field.le((Comparable<?>) castedValue);
            default:throw new IllegalArgumentException("unsupported operator:" + filter.getOperator());
        }
    }

    private Object castToRequireType(String key,String value){
        if(value == null) return null;
        return switch (key){
            case "date_order","date_delivery","date_recieve" -> LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME); //cast to localDateTime
            case "id","userId","user_id" -> Long.parseLong(value);
            default -> value;

        };
    }


}
