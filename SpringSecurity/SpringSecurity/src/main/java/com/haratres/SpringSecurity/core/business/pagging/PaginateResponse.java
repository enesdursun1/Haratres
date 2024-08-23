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
        this.pageCount =  (int) Math.ceil((double) TCount / pageSize);
        this.hasNext = pageIndex < pageCount - 1;
        this.hasPrevious = pageIndex > 0;
    }

}
