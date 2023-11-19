package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer employeeId;
    private String name;
    private String contactInfo;
    private String email;
    private String title;
    private String expertiseArea;
    private Integer employeeType;
}
