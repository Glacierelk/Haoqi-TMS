package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSummary {
    private Integer courseId;
    private String name;
    private Double revenue;
    private Integer status;
    private Double averageSatisfactionLevel;
    private String report;
}
