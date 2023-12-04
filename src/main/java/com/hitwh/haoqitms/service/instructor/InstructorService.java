package com.hitwh.haoqitms.service.instructor;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.Employee;

import java.util.List;

public interface InstructorService {

    /**
     * 查询讲师个人信息
     *
     * @param employee_id
     * @return
     */
    Employee getAllYourInfo(Integer employee_id);
}
