package com.hitwh.haoqitms.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManagerInstructorMapperTest {
    private final ManagerInstructorMapper ManagerInstructorMapper;

    @Autowired
    public ManagerInstructorMapperTest(ManagerInstructorMapper ManagerInstructorMapper) {
        this.ManagerInstructorMapper = ManagerInstructorMapper;
    }

    @Test
    void countTotalStudent() {
        System.out.println(ManagerInstructorMapper.countTotalInstructor());
    }
}
