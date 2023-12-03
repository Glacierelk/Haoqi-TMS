package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseApplication {
    private Integer applicationId;
    private Integer courseId;
    private Integer studentId;
    private String courseName;
    private String name;
    // 0 女 1 男
    private Integer gender;
    private String email;
    private String companyName;
    private String promoCode;
    private String jobPosition;
    private String skillLevel;
    private String contactInfo;
    // 0 待审批 1 通过 2 拒绝
    private Integer status;
}
