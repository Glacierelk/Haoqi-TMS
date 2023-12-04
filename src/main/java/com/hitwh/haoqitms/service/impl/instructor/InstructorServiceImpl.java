package com.hitwh.haoqitms.service.impl.instructor;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.Employee;
import com.hitwh.haoqitms.mapper.CourseMapper;
import com.hitwh.haoqitms.mapper.EmployeeMapper;
import com.hitwh.haoqitms.service.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public InstructorServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 获取所有个人信息
     * @param employee_id
     * @return
     */
    @Override
    public Employee getAllYourInfo(Integer employee_id){

        return employeeMapper.getAllYourInfo(employee_id);
    }
}
