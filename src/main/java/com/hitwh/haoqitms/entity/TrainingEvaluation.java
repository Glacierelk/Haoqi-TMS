package com.hitwh.haoqitms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingEvaluation {
    private Integer evaluationId;
    private Integer courseId;
    private Integer studentId;
    // 满意度 1 - 5
    private Integer satisfactionLevel;
    private String comments;
}
