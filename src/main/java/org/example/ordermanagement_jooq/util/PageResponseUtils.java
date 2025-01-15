package org.example.ordermanagement_jooq.util;

import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.data.response.PageResponse;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static generated_sources.Tables.ORDER;

public class PageResponseUtils {
    public static <T,S> PageResponse<T> toPageResponse(Pageable pageable, List<T> content,
                                                       Page<S> page){

        return PageResponse.<T>builder()
                .content(content)
                .pageNo(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    public static Condition buildCondition(List<FilterCondition> filters){
        if(filters.isEmpty() || filters == null){
            return DSL.noCondition(); // ko có câu truy vấn nào, where() sẽ đc loại bỏ
        }
        Condition condition = createCondition(filters.get(0));

        for(FilterCondition filter :filters){
            condition.and(createCondition(filter));
        }
        return condition;
    }
    private static Condition createCondition(FilterCondition filter){
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

    private static Object castToRequireType(String key,String value){
        if(value == null) return null;
        return switch (key){
            case "date_order","date_delivery","date_recieve","create_at" -> LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME); //cast to localDateTime
            case "id","user_id","quantity","sold_quantity" -> Long.parseLong(value);
            case "selling_price","import_price" -> BigDecimal.valueOf(Long.parseLong(value));
            default -> value;

        };
    }
}
