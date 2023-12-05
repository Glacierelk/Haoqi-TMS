package com.hitwh.haoqitms.controller.executor;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.executor.ExecutorCourseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/executor/courseApplication")
@RestController
public class ExecutorCourseApplicationController {
    private final ExecutorCourseApplicationService executorCourseApplicationService;

    @Autowired
    public ExecutorCourseApplicationController(ExecutorCourseApplicationService executorCourseApplicationService) {
        this.executorCourseApplicationService = executorCourseApplicationService;
    }

    /**
     * 获取所有课程申请信息
     *
     * @return 所有课程申请信息
     */
    @GetMapping("/allApplications")
    public ResultInfo getAllApplications() {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(true);
            info.setData(executorCourseApplicationService.getAllCourseApplication());
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("获取课程申请信息失败");
        }
        return info;
    }

    /**
     * 根据执行人id获取课程申请信息
     * @param executorId 执行人id
     * @return 课程申请信息
     */
    @GetMapping("/allApplications/{executorId}")
    public ResultInfo getApplicationsByExecutorId(@PathVariable(name="executorId") Integer executorId) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(true);
            info.setData(executorCourseApplicationService.getCourseApplicationByExecutorId(executorId));
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("获取课程申请信息失败");
        }
        return info;
    }

    /**
     * 通过学员课程审批
     * @param applicationId 课程申请id
     */
    @GetMapping("/approve/{applicationId}")
    public ResultInfo approveApplication(@PathVariable(name="applicationId") Integer applicationId) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(executorCourseApplicationService.approveApplication(applicationId));
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("通过课程申请失败");
        }
        return info;
    }

    /**
     * 拒绝学员课程审批
     * @param applicationId 课程申请id
     */
    @GetMapping("/reject/{applicationId}")
    public ResultInfo rejectApplication(@PathVariable(name="applicationId") Integer applicationId) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(executorCourseApplicationService.rejectApplication(applicationId));
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("拒绝课程申请失败");
        }
        return info;
    }
}
