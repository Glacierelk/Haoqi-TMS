package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.TrainingEvaluation;
import com.hitwh.haoqitms.mapper.TrainingEvaluationMapper;
import com.hitwh.haoqitms.service.TrainingEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingEvaluationServiceImpl implements TrainingEvaluationService {
    private final TrainingEvaluationMapper trainingEvaluationMapper;

    @Autowired
    public TrainingEvaluationServiceImpl(TrainingEvaluationMapper trainingEvaluationMapper) {
        this.trainingEvaluationMapper = trainingEvaluationMapper;
    }


    @Override
    public Boolean createTrainingEvaluation(TrainingEvaluation trainingEvaluation) {
        return trainingEvaluationMapper.createTrainingEvaluation(trainingEvaluation);
    }
}
