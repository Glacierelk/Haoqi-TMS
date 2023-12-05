package com.hitwh.haoqitms.controller.instructor;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.instructor.InstructorCourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/instructor/course")
@RestController
public class InstructorCourseController {


    private final InstructorCourseService instructorCourseService;

    @Autowired
    public InstructorCourseController(InstructorCourseService instructorCourseService) {
        this.instructorCourseService = instructorCourseService;
    }

    /**
     * 获取讲师所有课程的信息
     * @param
     * @return
     */
    @GetMapping("/yourCourse/{employee_id}")
    public ResultInfo getAllYourCourse(@PathVariable Integer employee_id){
        ResultInfo info = new ResultInfo();
        //Object obj = request.getSession().getAttribute("employee_id");
        //Integer employee_id = Integer.valueOf((Integer)obj);
        try {
            List<Course> course = instructorCourseService.getAllYourCourse(employee_id);
            info.setFlag(true);
            info.setData(course);
        }catch (Exception e){
            e.printStackTrace();
            info.setFlag(false);
            info.setErrorMsg("fail to get all infos");
        }
        return info;
    }

}
