package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.Pagination;
import com.hitwh.haoqitms.entity.TrainingApplication;
import org.springframework.stereotype.Service;

@Service
public interface TrainingApplicationService {
    Boolean createTrainingApplication(TrainingApplication trainingApplication);
    String selectCompanyNameByPromoCode(String promoCode);

    /**
     * 查询培训申请
     * @param trainingApplication 查询条件
     * @param pageSize 每页大小
     * @param currentPage 当前页
     * @return 分页对象
     */
    Pagination selectTrainingApplications(TrainingApplication trainingApplication, Integer pageSize, Integer currentPage);
}
