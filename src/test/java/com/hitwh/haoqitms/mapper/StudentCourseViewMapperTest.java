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
    void updateStudentPayStatus() {
        System.out.println(studentCourseViewMapper.updateStudentPayStatus(1, 4));
    }

    @Test
    void updateStudentAttendanceStatus() {
        System.out.println(studentCourseViewMapper.updateStudentAttendanceStatus(1, 4, true));
    }

    @Test
    void getStudentCourse() {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourseId(4);
        //studentCourse.setPhone("13587168037");
//        studentCourse.setName("%周浩%");
        studentCourse.setCompanyName("%华%");
        List<StudentCourse> studentCourseList = studentCourseViewMapper.getStudentCourse(studentCourse, 10, 0);
        for (StudentCourse studentCourse1 : studentCourseList) {
            System.out.println(studentCourse1);
        }
    }

    @Test
    void getStudentCourseCount() {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourseId(4);
//        studentCourse.setPhone("13587168037");
        System.out.println(studentCourseViewMapper.getStudentCourseCount(studentCourse));
    }
}