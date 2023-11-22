package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private Integer total;
    private Integer pageSize;
    private Integer currentPage;
    private List<?> data;
}
