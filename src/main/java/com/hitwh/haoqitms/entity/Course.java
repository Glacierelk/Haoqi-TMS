package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer courseId;
    private String name;
    private String companyName;
    private String description;

    // TODO: change to Date type
    private Timestamp startDate;
    private Timestamp endDate;

    private String location;
    private Double course_fee;
    private Integer instructorId;
    private Integer executorId;
    private String report;
}
