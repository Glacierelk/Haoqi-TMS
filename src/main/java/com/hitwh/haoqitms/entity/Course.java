package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer courseId;
    private String name;
    private String companyName;
    private String description;

    // TODO: change to Date type
    private String startDate;
    private String endDate;

    private String location;
    private BigDecimal course_fee;
    private Integer instructorId;
    private Integer executorId;
}
