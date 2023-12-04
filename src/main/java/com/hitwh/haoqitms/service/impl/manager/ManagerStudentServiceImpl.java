package com.hitwh.haoqitms.service.impl.manager;

import com.hitwh.haoqitms.mapper.StudentMapper;
import com.hitwh.haoqitms.service.manager.ManagerStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerStudentServiceImpl implements ManagerStudentService {

    private final StudentMapper studentMapper;

    @Autowired
    public ManagerStudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public Integer countTotalStudent() {
        return studentMapper.countTotalStudent();
    }
}
