package com.hitwh.haoqitms.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeMapperTest {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeMapperTest(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Test
    void getAllYourInfo() {
        System.out.println(employeeMapper.getAllYourInfo(144));
    }
}