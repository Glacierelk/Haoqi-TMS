package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.StudentCourse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentCourseViewMapperTest {
    private final StudentCourseViewMapper studentCourseViewMapper;

    @Autowired
    public StudentCourseViewMapperTest(StudentCourseViewMapper studentCourseViewMapper) {
        this.studentCourseViewMapper = studentCourseViewMapper;
    }

    @Test
    void getStudentCourseByCourseId() {
        List<StudentCourse> studentCourseList = studentCourseViewMapper.getStudentCourseByCourseId(4);
        for (StudentCourse studentCourse : studentCourseList) {
            System.out.println(studentCourse);
        }
    }

    @Test
    void updateStudentPayStatus() {
        System.out.println(studentCourseViewMapper.updateStudentPayStatus(1, 4, false));
    }

    @Test
    void updateStudentAttendanceStatus() {
        System.out.println(studentCourseViewMapper.updateStudentAttendanceStatus(1, 4, true));
    }
}