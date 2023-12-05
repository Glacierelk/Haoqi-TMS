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
    private String username;
    private String password;
    private String contactInfo;
    private String email;
    private String title;
    private String expertiseArea;

    // 0：经理
    // 1：执行人
    // 2：讲师
    // 3：现场工作人员
    private Integer employeeType;
}
