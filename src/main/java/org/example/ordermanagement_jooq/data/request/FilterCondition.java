package org.example.ordermanagement_jooq.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class FilterCondition {
    @NotBlank(message = "field is not blank!")
    String field;
    @NotBlank(message = "operator is not blank!")
    String operator;
    @NotBlank(message = "value is not blank!")
    String value;
}
