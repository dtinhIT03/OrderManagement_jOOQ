package org.example.ordermanagement_jooq.data.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class FilterCondition {
    String field;
    String operator;
    String value;
}
