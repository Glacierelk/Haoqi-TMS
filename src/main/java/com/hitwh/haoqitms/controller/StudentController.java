package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.service.StudentService;
import com.hitwh.haoqitms.service.TrainingApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    /**
     * 创建学员(前端首先要判断学员填报的团报码和公司名称是否合法)
     * @param request
     * @param student
     * @return
     */
    @PostMapping("/create")
    public ResultInfo create(HttpServletRequest request, @RequestBody Student student){
        ResultInfo info = new ResultInfo();
        try {
            Boolean result = studentService.createStudent(student);
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
}
