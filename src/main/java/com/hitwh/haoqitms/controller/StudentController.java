package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.service.StudentService;
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
            e.printStackTrace();
        }
        return info;
    }
}
