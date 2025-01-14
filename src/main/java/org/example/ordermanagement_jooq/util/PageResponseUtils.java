package org.example.ordermanagement_jooq.util;

import org.example.ordermanagement_jooq.data.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
}
