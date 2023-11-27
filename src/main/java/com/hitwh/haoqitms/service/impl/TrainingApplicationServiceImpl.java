package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.Pagination;
import com.hitwh.haoqitms.entity.TrainingApplication;
import com.hitwh.haoqitms.mapper.TrainingApplicationMapper;
import com.hitwh.haoqitms.service.TrainingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingApplicationServiceImpl implements TrainingApplicationService {

    private final TrainingApplicationMapper trainingApplicationMapper;

    @Autowired
    public TrainingApplicationServiceImpl(TrainingApplicationMapper trainingApplicationMapper) {
        this.trainingApplicationMapper = trainingApplicationMapper;
    }


    @Override
    public Boolean createTrainingApplication(TrainingApplication trainingApplication) {
        return trainingApplicationMapper.createTrainingApplication(trainingApplication);
    }

    @Override
    public String selectCompanyNameByPromoCode(String promoCode) {
        return trainingApplicationMapper.selectCompanyNameByPromoCode(promoCode);
    }

    @Override
    public Pagination selectTrainingApplications(TrainingApplication trainingApplication, Integer pageSize, Integer currentPage) {
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setCurrentPage(currentPage);
        pagination.setTotal(trainingApplicationMapper.selectTrainingApplicationsCount(trainingApplication));
        pagination.setData(trainingApplicationMapper.selectTrainingApplications(trainingApplication, pageSize, (currentPage - 1) * pageSize));
        return pagination;
    }

}
