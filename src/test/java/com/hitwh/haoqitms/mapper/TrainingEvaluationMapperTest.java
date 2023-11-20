package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.TrainingEvaluation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TrainingEvaluationMapperTest {
    private final TrainingEvaluationMapper trainingEvaluationMapper;

    @Autowired
    public TrainingEvaluationMapperTest(TrainingEvaluationMapper trainingEvaluationMapper) {
        this.trainingEvaluationMapper = trainingEvaluationMapper;
    }

    @Test
    void getTrainingEvaluationByCourseId() {
        List<TrainingEvaluation> trainingEvaluationList = trainingEvaluationMapper.getTrainingEvaluationByCourseId(4);
        for (TrainingEvaluation trainingEvaluation : trainingEvaluationList) {
            System.out.println(trainingEvaluation);
        }
    }

    @Test
    void deleteTrainingEvaluation() {
        Boolean result = trainingEvaluationMapper.deleteTrainingEvaluation(1);
        System.out.println(result);
    }
}