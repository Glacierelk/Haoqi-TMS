package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutorSummary {
    private Integer executorId;
    private String executorName;
    private Integer courseCount;
}
