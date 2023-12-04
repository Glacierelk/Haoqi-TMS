package com.hitwh.haoqitms.controller.manager;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.TrainingApplication;
import com.hitwh.haoqitms.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/manager/application")
@RestController
public class ManagerTrainingApplicationController {
    private final HomeService homeService;

    @Autowired
    public ManagerTrainingApplicationController(HomeService homeService) {
        this.homeService = homeService;
    }

    /**
     * 获取培训申请列表
     * @param companyName 企业名称
     * @param applicationDate 申请日期
     * @param status 申请状态
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 培训申请列表
     */
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
            resultInfo.setData(homeService.selectTrainingApplications(trainingApplication, pageSize, currentPage));
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取培训申请列表失败");
        }
        return resultInfo;
    }

    /**
     * 通过培训申请
     * @param applicationId 培训申请编号
     * @return 操作是否成功
     */
    @PutMapping("/accept/{application_id}")
    public ResultInfo acceptApplication(@PathVariable("application_id") Integer applicationId){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(homeService.acceptApplication(applicationId));
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("接受培训申请失败");
        }
        return resultInfo;
    }

    /**
     * 拒绝培训申请
     * @param applicationId 培训申请编号
     * @return 操作是否成功
     */
    @PutMapping("/reject/{application_id}")
    public ResultInfo rejectApplication(@PathVariable("application_id") Integer applicationId){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(homeService.rejectApplication(applicationId));
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("拒绝培训申请失败");
        }
        return resultInfo;
    }

}
