package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.TrainingEvaluation;

public interface TrainingEvaluationService {
    /**
     * 创建培训评价[学员]
     * @param trainingEvaluation
     * @return
     */
    Boolean createTrainingEvaluation(TrainingEvaluation trainingEvaluation);
}
