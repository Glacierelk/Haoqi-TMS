package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.TrainingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TrainingApplicationMapperTest {
    private final TrainingApplicationMapper trainingApplicationMapper;

    @Autowired
    TrainingApplicationMapperTest(TrainingApplicationMapper trainingApplicationMapper) {
        this.trainingApplicationMapper = trainingApplicationMapper;
    }

    @Test
    void createTrainingApplication() {
    }

    @Test
    void selectCompanyNameByPromoCode() {
    }

    @Test
    void selectTrainingApplications() {
        TrainingApplication trainingApplication = new TrainingApplication();
        trainingApplication.setCompanyName("宇");
        trainingApplication.setStatus(0);
        trainingApplication.setApplicationDate("2020-12-01");
        List<TrainingApplication> trainingApplications = trainingApplicationMapper.selectTrainingApplications(trainingApplication, 10, 0);
        System.out.println(trainingApplications.size());
//        for (TrainingApplication trainingApplication1 : trainingApplications) {
//            System.out.println(trainingApplication1);
//        }
    }

    @Test
    void selectTrainingApplicationsCount() {
        TrainingApplication trainingApplication = new TrainingApplication();
        System.out.println(trainingApplicationMapper.selectTrainingApplicationsCount(trainingApplication));
    }
}