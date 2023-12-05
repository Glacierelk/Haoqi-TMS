package com.hitwh.haoqitms.controller.instructor;

import com.hitwh.haoqitms.entity.Employee;
import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.instructor.InstructorCourseService;
import com.hitwh.haoqitms.service.instructor.InstructorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/instructor/my")
@RestController
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/infomation/{employee_id}")
    public ResultInfo getAllYourInfo(@PathVariable Integer employee_id){

        ResultInfo info = new ResultInfo();
        //Object obj = request.getParameter("employee_id");
        //Integer employee_id = Integer.valueOf((Integer)obj);
        try {
            Employee employee = instructorService.getAllYourInfo(employee_id);
            info.setFlag(true);
            info.setData(employee);
        }catch (Exception e){
            e.printStackTrace();
            info.setFlag(false);
            info.setErrorMsg("fail to get all infos");
        }
        return info;
    }
}
