package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingApplication {
    private Integer applicationId;
    private Integer courseId;
    private String companyName;
    private String promoCode;
    private Double budget;
    private String contactInfo;
    private String applicationDate;

    // 0: 等待审批
    // 1: 接受
    // 2: 拒绝
    // 3: 已完成
    private Integer status;
    private String description;
}
