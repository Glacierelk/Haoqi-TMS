package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.CourseList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    void getCourses() {
        CourseList courseList = new CourseList();
        courseList.setName("%董%");
//        courseList.setInstructorName("%周%");
//        courseList.setCompanyName("%信%");
//        courseList.setStartDate(new Date(Timestamp.valueOf("2020-01-01 00:00:00").getTime()));
        courseList.setStartDate("2022-01-01");
        System.out.println(courseList.getStartDate());
        List<CourseList> courses = courseMapper.getCourses(courseList, 10, 0);
        for (CourseList course : courses) {
            System.out.println(course);
        }
    }

    @Test
    void getCoursesCount() {
    }

    @Test
    void insertCourse() {
        Course course = new Course();
        course.setName("这是一个测试课程");
        course.setCompanyName("宇宁有限责任公司");
        course.setDescription("test");
        course.setStartDate("2020-01-01");
        course.setEndDate("2020-01-01");
        course.setLocation("test");
        course.setCourseFee(123.456);
        course.setInstructorId(4);
        course.setExecutorId(1);
        System.out.println(courseMapper.insertCourse(course));
    }

    @Test
    void deleteCourseByCourseId() {
        System.out.println(courseMapper.deleteCourseByCourseId(102));
    }

    @Test
    void updateCourse() {
        Course course = new Course();
        course.setCourseId(103);
        course.setName("这是一个测试课程");
        course.setCompanyName("宇宁有限责任公司");
        course.setDescription("test");
        course.setStartDate("2021-01-01");
        course.setEndDate("2020-01-01");
        course.setLocation("test");
        course.setCourseFee(12345.6);
        course.setInstructorId(4);
        course.setExecutorId(2);
        System.out.println(courseMapper.updateCourse(course));
    }

    @Test
    void getCourseByName() {
        courseMapper.getCourseByName(null, 10, 0).forEach(System.out::println);
    }

    @Test
    void getCourseCountByName() {
        System.out.println(courseMapper.getCourseCountByName(null));
    }

    @Test
    void insertRevenue() {
        for (int i = 1; i <= 1000; ++i) {
            courseMapper.updateRevenueByCourseId(i, 1234.15);
        }
    }
}