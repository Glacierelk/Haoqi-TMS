package com.hitwh.haoqitms.controller.executor;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.entity.StudentCourse;
import com.hitwh.haoqitms.service.CourseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/executor/courseApplication")
@RestController
public class ExecutorCourseApplicationController {
    private final CourseApplicationService courseApplicationService;

    @Autowired
    public ExecutorCourseApplicationController(CourseApplicationService courseApplicationService) {
        this.courseApplicationService = courseApplicationService;
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
            info.setData(courseApplicationService.getAllCourseApplication());
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
            info.setFlag(courseApplicationService.approveApplication(applicationId));
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
            info.setFlag(courseApplicationService.rejectApplication(applicationId));
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("拒绝课程申请失败");
        }
        return info;
    }
}
