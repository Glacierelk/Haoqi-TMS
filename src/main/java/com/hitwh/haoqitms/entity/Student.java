package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer studentId;
    private String name;
    private Integer gender;
    private String email;
    private String companyName;
    private String jobPosition;
    private String skillLevel;
    private String contactInfo;
}
