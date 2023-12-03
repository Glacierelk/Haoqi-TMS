package com.hitwh.haoqitms.service;


import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.mapper.StudentMapper;

public interface StudentService {
    /**
     * 创建学员
     * @param student
     * @return
     */
    Boolean createStudent(Student student);
}
