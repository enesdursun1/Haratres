package com.haratres.SpringSecurity.core.business.pagging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {

   private int pageIndex , pageSize;


}
