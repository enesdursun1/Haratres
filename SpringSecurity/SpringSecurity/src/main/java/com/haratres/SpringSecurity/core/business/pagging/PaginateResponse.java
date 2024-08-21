package com.haratres.SpringSecurity.core.business.pagging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginateResponse<T> {


    List<T> items;

    private int pageCount;
    private boolean hasNext, hasPrevious;


    public PaginateResponse(int pageIndex, int pageSize,long TCount,List<T> items) {

        this.items = items;

        int totalPages = (int) Math.ceil((double) TCount / pageSize);
        this.pageCount = totalPages;
        this.hasNext = pageIndex < totalPages - 1;
        this.hasPrevious = pageIndex > 0;
    }

}
