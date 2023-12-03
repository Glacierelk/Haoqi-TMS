package com.hitwh.haoqitms.controller.executor;

import com.hitwh.haoqitms.entity.Employee;
import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.executor.ExecutorInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/executor/Instructor")
@RestController
public class ExecutorInstructorController {
    private final ExecutorInstructorService executorInstructorService;

    @Autowired
    public ExecutorInstructorController(ExecutorInstructorService executorEmployeeService) {
        this.executorInstructorService = executorEmployeeService;
    }


    @PostMapping("/add")
    public ResultInfo addInstructor(Employee instructor){
        ResultInfo resultInfo = new ResultInfo();
        if (executorInstructorService.insertInstructor(instructor)){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("fail to insert!");
        }
        return  resultInfo;
    }

    @GetMapping("/delete")
    public ResultInfo deleteInstructor(Integer id){
        ResultInfo resultInfo = new ResultInfo();
        if(executorInstructorService.deleteInstructor(id)){
            resultInfo.setFlag(true);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("fail to delete!");
        }
        return resultInfo;
    }

    @GetMapping("/search")
    public List<Employee> searchInstructor(String name){
        return executorInstructorService.searchByName(name);
    }

    @PostMapping("/update")
    public ResultInfo updateInstructor(Employee instructor){
        ResultInfo resultInfo = new ResultInfo();
        if(executorInstructorService.update(instructor)){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("fail to update");
        }
        return  resultInfo;
    }

    @GetMapping("/getAllTeachers")
    public List<Employee> getAllTeachers(){
        return executorInstructorService.getAllTeachers();
    }

}
