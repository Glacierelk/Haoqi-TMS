package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.TrainingEvaluation;
import com.hitwh.haoqitms.mapper.TrainingEvaluationMapper;
import com.hitwh.haoqitms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final TrainingEvaluationMapper trainingEvaluationMapper;

    @Autowired
    public StudentServiceImpl(TrainingEvaluationMapper trainingEvaluationMapper) {
        this.trainingEvaluationMapper = trainingEvaluationMapper;
    }


    @Override
    public Boolean createTrainingEvaluation(TrainingEvaluation trainingEvaluation) {
        return trainingEvaluationMapper.createTrainingEvaluation(trainingEvaluation);
    }
}
