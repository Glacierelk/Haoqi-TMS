package com.hitwh.haoqitms.service.impl.manager;

import com.hitwh.haoqitms.mapper.ManagerInstructorMapper;
import com.hitwh.haoqitms.mapper.ManagerStudentMapper;
import com.hitwh.haoqitms.service.ManagerInstructorService;
import com.hitwh.haoqitms.service.ManagerStudentService;
import org.springframework.beans.factory.annotation.Autowired;

public class ManagerInstructorServiceImpl implements ManagerInstructorService {

    private final com.hitwh.haoqitms.mapper.ManagerInstructorMapper ManagerInstructorMapper;

    @Autowired
    public ManagerInstructorServiceImpl(ManagerInstructorMapper ManagerInstructorMapper) {
        this.ManagerInstructorMapper = ManagerInstructorMapper;
    }

    @Override
    public Integer countTotalInstructor() {
        return ManagerInstructorMapper.countTotalInstructor();
    }
}
