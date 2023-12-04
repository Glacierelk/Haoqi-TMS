package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.mapper.CourseMapper;
import com.hitwh.haoqitms.mapper.ManagerStudentMapper;
import com.hitwh.haoqitms.mapper.StudentMapper;
import com.hitwh.haoqitms.service.ManagerStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class ManagerStudentServiceImpl implements ManagerStudentService {

    private final ManagerStudentMapper ManagerStudentMapper;

    @Autowired
    public ManagerStudentServiceImpl(ManagerStudentMapper ManagerStudentMapper) {
        this.ManagerStudentMapper = ManagerStudentMapper;
    }

    @Override
    public Integer countTotalStudent() {
        return ManagerStudentMapper.countTotalStudent();
    }
}
