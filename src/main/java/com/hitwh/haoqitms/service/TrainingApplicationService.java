package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.TrainingApplication;
import org.springframework.stereotype.Service;

@Service
public interface TrainingApplicationService {
    Boolean createTrainingApplication(TrainingApplication trainingApplication);
    String selectCompanyNameByPromoCode(String promoCode);
}
