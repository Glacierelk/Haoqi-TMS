package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.TrainingEvaluation;
import com.hitwh.haoqitms.service.TrainingEvaluationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/trainingEvaluation")
@RestController
public class TrainingEvaluationController {
    private final TrainingEvaluationService trainingEvaluationService;

    @Autowired
    public TrainingEvaluationController(TrainingEvaluationService trainingEvaluationService) {
        this.trainingEvaluationService = trainingEvaluationService;
    }

    @PostMapping("/create")
    public ResultInfo create(HttpServletRequest request, @RequestBody TrainingEvaluation trainingEvaluation){
        ResultInfo info = new ResultInfo();
        try {
            Boolean result = trainingEvaluationService.createTrainingEvaluation(trainingEvaluation);
            if (result) {
                info.setFlag(true);
            } else {
                info.setFlag(false);
                info.setErrorMsg("创建失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("创建失败,请检查是否已经评价过");
        }

        return info;
    }
}
