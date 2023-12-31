package com.hitwh.haoqitms.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userEmployeeId;
    private Integer userStudentId;
    private String username;
    private String password;
    // 0: 员工 1: 学员
    private Integer userType;
}
