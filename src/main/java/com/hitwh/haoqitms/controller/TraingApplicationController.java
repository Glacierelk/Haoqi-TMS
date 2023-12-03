package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.TrainingApplication;
import com.hitwh.haoqitms.service.TrainingApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/trainingApplication")
@RestController
public class TraingApplicationController {
    private final TrainingApplicationService trainingApplicationService;

    @Autowired
    public TraingApplicationController(TrainingApplicationService trainingApplicationService) {
        this.trainingApplicationService = trainingApplicationService;
    }

    @PostMapping("/create")
    public ResultInfo create(HttpServletRequest request, @RequestBody TrainingApplication trainingApplication){
        ResultInfo info = new ResultInfo();
        try {
            Boolean result = trainingApplicationService.createTrainingApplication(trainingApplication);
            if (result) {
                info.setFlag(true);
            } else {
                info.setFlag(false);
                info.setErrorMsg("创建失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("创建失败");
        }
        return info;
    }
    @PostMapping("/checkIdentity")
    public ResultInfo checkIdentity(HttpServletRequest request, @RequestBody TrainingApplication trainingApplication){
        ResultInfo info = new ResultInfo();
        try {
            String result = trainingApplicationService.selectCompanyNameByPromoCode(trainingApplication.getPromoCode());
            if (result != null) {
                if (result.equals(trainingApplication.getCompanyName())) {
                    info.setFlag(true);
                } else {
                    info.setFlag(false);
                    info.setErrorMsg("团报码不匹配");
                }
            } else {
                info.setFlag(false);
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("无效团报码");
            e.printStackTrace();
        }
        return info;
    }

}
