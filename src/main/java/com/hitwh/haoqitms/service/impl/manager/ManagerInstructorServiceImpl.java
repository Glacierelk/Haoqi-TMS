package com.hitwh.haoqitms.service.impl.manager;

import com.hitwh.haoqitms.mapper.EmployeeMapper;
import com.hitwh.haoqitms.service.manager.ManagerInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerInstructorServiceImpl implements ManagerInstructorService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public ManagerInstructorServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Integer countTotalInstructor() {
        return employeeMapper.countTotalInstructor();
    }
}
