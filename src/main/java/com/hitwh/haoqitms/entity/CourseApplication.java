package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseApplication {
    private Integer courseId;
    private Integer applicationId;
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

}
