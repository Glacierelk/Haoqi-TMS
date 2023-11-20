package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/staff")
@RestController
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/students_list/{course_id}")
    public ResultInfo getStudentList(HttpServletRequest request, @PathVariable("course_id") Integer courseId) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(true);
            info.setData(staffService.getStudentsByCourseId(courseId));
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("获取学生列表失败");
        }
        return info;
    }

    @GetMapping("/evaluation/{course_id}")
    public ResultInfo getEvaluation(HttpServletRequest request, @PathVariable("course_id") Integer courseId) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(true);
            info.setData(staffService.getTrainingEvaluationByCourseId(courseId));
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("获取评价列表失败");
        }
        return info;
    }

    @GetMapping("/report/{course_id}")
    public ResultInfo getReport(HttpServletRequest request, @PathVariable("course_id") Integer courseId) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(true);
            info.setData(staffService.getCourseSurveyReport(courseId));
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("获取调查报告失败");
        }
        return info;
    }

    @PutMapping("/pay/{student_id}/{course_id}/{paid}")
    public ResultInfo updatePayStatus(HttpServletRequest request,
                                      @PathVariable("student_id") Integer studentId,
                                      @PathVariable("course_id") Integer courseId,
                                      @PathVariable("paid") Boolean paid) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(staffService.updateStudentPayStatus(studentId, courseId, paid));
            if (!info.getFlag()) {
                info.setErrorMsg("更新缴费状态失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("更新缴费状态失败");
        }
        return info;
    }

    @PutMapping("/attendance/{student_id}/{course_id}/{attendance}")
    public ResultInfo updateAttendanceStatus(HttpServletRequest request,
                                             @PathVariable("student_id") Integer studentId,
                                             @PathVariable("course_id") Integer courseId,
                                             @PathVariable("attendance") Boolean attendance) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(staffService.updateStudentAttendanceStatus(studentId, courseId, attendance));
            if (!info.getFlag()) {
                info.setErrorMsg("更新考勤状态失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("更新考勤状态失败");
        }
        return info;
    }

    @PutMapping("/report/{course_id}")
    public ResultInfo updateReport(HttpServletRequest request,
                                   @PathVariable("course_id") Integer courseId,
                                   @RequestBody String report) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(staffService.updateCourseSurveyReport(courseId, report));
            if (!info.getFlag()) {
                info.setErrorMsg("更新调查报告失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("更新调查报告失败");
        }
        return info;
    }

    @DeleteMapping("/evaluation/{evaluation_id}")
    public ResultInfo deleteEvaluation(HttpServletRequest request,
                                       @PathVariable("evaluation_id") Integer evaluationId) {
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(staffService.deleteTrainingEvaluation(evaluationId));
            if (!info.getFlag()) {
                info.setErrorMsg("删除评价失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("删除评价失败");
        }
        return info;
    }

}
