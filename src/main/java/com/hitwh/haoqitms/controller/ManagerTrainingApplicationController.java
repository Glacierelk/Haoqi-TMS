package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.TrainingApplication;
import com.hitwh.haoqitms.service.TrainingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/manager/application")
@RestController
public class ManagerTrainingApplicationController {
    private final TrainingApplicationService trainingApplicationService;

    @Autowired
    public ManagerTrainingApplicationController(TrainingApplicationService trainingApplicationService) {
        this.trainingApplicationService = trainingApplicationService;
    }

    @GetMapping("/list/{company_name}/{application_date}/{status}/{pageSize}/{currentPage}")
    public ResultInfo selectApplications(@PathVariable("company_name") String companyName,
                                         @PathVariable("application_date") String applicationDate,
                                         @PathVariable("status") Integer status,
                                         @PathVariable("pageSize") Integer pageSize,
                                         @PathVariable("currentPage") Integer currentPage){
        TrainingApplication trainingApplication = new TrainingApplication();
        trainingApplication.setCompanyName(companyName.equals("null") ? null : companyName);
        trainingApplication.setApplicationDate(applicationDate.equals("null") ? null : applicationDate);
        trainingApplication.setStatus(status);

        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(trainingApplicationService.selectTrainingApplications(trainingApplication, pageSize, currentPage));
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取培训申请列表失败");
        }
        return resultInfo;
    }

}
