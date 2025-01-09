package org.example.ordermanagement_jooq.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class PageResponse<T> {
    private long totalElements;
    private long totalPages;
    private int pageNo;
    private int pageSize;
    private List<T> content;
}
