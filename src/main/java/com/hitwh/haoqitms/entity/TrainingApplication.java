package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingApplication {
    private Integer applicationId;
    private String companyName;
    private String promo_code;
    private BigDecimal budget;
    private String contactInfo;

    // TODO: change type
    private String applicationDate;

    // 0: 等待审批
    // 1: 接受
    // 2: 拒绝
    private Integer status;
}
