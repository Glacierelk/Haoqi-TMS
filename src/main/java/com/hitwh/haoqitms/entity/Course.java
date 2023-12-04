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
    // 课程名称
    private String name;
    private String companyName;
    private String description;
    private String startDate;
    private String endDate;
    private String location;
    private Double courseFee;
    private Integer instructorId;
    // 讲师名称
    private String instructorName;
    private Integer executorId;
    // 执行人名称
    private String executorName;
    private String report;
    private Double revenue;
}
