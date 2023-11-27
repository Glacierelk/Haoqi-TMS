package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.ExecutorCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/executor/course")
@RestController
public class ExecutorCourseController {
    private final ExecutorCourseService executorCourseService;

    @Autowired
    public ExecutorCourseController(ExecutorCourseService executorCourseService) {
        this.executorCourseService = executorCourseService;
    }

    /**
     * 创建课程
     *
     * @param course 课程信息
     * @return 创建结果
     */
    @PostMapping("/add")
    public ResultInfo addCourse(@RequestBody Course course){
        ResultInfo resultInfo = new ResultInfo();
        if (executorCourseService.addCourse(course)){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("创建失败!");
        }
        return  resultInfo;
    }

    /**
     * 删除课程
     *
     * @param id 课程id
     * @return 删除结果
     */
    @DeleteMapping("/delete/{course_id}")
    public ResultInfo deleteCourse(@PathVariable("course_id") Integer id){
        ResultInfo resultInfo = new ResultInfo();
        if(executorCourseService.deleteCourse(id)){
            resultInfo.setFlag(true);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("删除失败!");
        }
        return resultInfo;
    }

    /**
     * 更新课程信息
     *
     * @param course 课程信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public ResultInfo updateCourse(@RequestBody Course course){
        ResultInfo resultInfo = new ResultInfo();
        if(executorCourseService.updateCourse(course)){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("更新失败!");
        }
        return resultInfo;
    }
}
