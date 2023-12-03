package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.mapper.StudentMapper;
import com.hitwh.haoqitms.service.ManagerStudentService;
import com.hitwh.haoqitms.service.StudentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;


@NoArgsConstructor
@RequestMapping("/manager/student")
@RestController
public class ManagerStudentController {

    private ManagerStudentService managerStudentService;


    /**
     * 查看培训学员信息
     *
     * @return 所有学员数量
     */
    @GetMapping("/total")
    public Integer countTotalStudent() {

        //Integer studentsTotalNumber = StudentMapper.countTotalStudent();
        return managerStudentService.countTotalStudent();
    }


}
