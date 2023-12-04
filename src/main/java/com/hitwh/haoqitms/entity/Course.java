package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer applicationId;
    private Integer courseId;
    private String name;
    private String companyName;
    private String description;
    private String startDate;
    private String endDate;
    private String location;
    private Double courseFee;
    private Integer instructorId;
    private Integer executorId;
    private String report;
    private Double revenue;
}
