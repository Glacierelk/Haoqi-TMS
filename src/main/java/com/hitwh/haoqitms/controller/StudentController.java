package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.TrainingEvaluation;
import com.hitwh.haoqitms.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/createTrainingEvaluation")
    public ResultInfo create(HttpServletRequest request, @RequestBody TrainingEvaluation trainingEvaluation){
        ResultInfo info = new ResultInfo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        trainingEvaluation.setEvaluationTime(LocalDateTime.now().format(formatter));
        System.out.println(trainingEvaluation);
        try {
            boolean flag = studentService.createTrainingEvaluation(trainingEvaluation);
            if(flag){
                info.setFlag(true);
            }else{
                info.setFlag(false);
                info.setErrorMsg("未签到或未付款或已评价，无法创建课程评价");
            }
        }catch (Exception e){
            info.setFlag(false);
            info.setErrorMsg("创建课程评价失败");
        }
        return info;
    }

    /**
     * 根据学生id查询课程列表
     */
    @GetMapping("/getCourseList/{studentId}")
    public ResultInfo getCourseList(@PathVariable(value="studentId") Integer studentId){
        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(true);
            info.setData(studentService.getCourseListByStudentId(studentId));
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("获取课程列表失败");
            e.printStackTrace();
        }
        return info;
    }
}
