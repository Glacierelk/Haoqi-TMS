package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.service.ManagerInstructorService;
import com.hitwh.haoqitms.service.ManagerStudentService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@NoArgsConstructor
@RequestMapping("/manager/instructor")
@RestController
public class ManagerInstructorController {

    private ManagerInstructorService managerInstructorService;


    /**
     * 查看讲师信息
     *
     * @return 讲师数量
     */
    @GetMapping("/total")
    public Integer countTotalInstructor() {

        //Integer studentsTotalNumber = StudentMapper.countTotalStudent();
        return managerInstructorService.countTotalInstructor();
    }


}
