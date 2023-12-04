package com.hitwh.haoqitms.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManagerStudentMapperTest {
    private final ManagerStudentMapper ManagerStudentMapper;

    @Autowired
    public ManagerStudentMapperTest(ManagerStudentMapper ManagerStudentMapper) {
        this.ManagerStudentMapper = ManagerStudentMapper;
    }

    @Test
    void countTotalStudent() {
        System.out.println(ManagerStudentMapper.countTotalStudent());
    }
}
