package com.hitwh.haoqitms.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseMapperTest {
    private final CourseMapper courseMapper;

    @Autowired
    public CourseMapperTest(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Test
    void getReportByCourseId() {
        System.out.println(courseMapper.getReportByCourseId(4));
    }

    @Test
    void updateReportByCourseId() {
        System.out.println(courseMapper.updateReportByCourseId(4, "test"));
    }
}