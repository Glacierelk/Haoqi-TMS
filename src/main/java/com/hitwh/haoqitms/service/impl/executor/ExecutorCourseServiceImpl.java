package com.hitwh.haoqitms.service.impl.executor;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.StudentCourse;
import com.hitwh.haoqitms.mapper.CourseMapper;
import com.hitwh.haoqitms.mapper.StudentCourseViewMapper;
import com.hitwh.haoqitms.service.ExcelService;
import com.hitwh.haoqitms.service.executor.ExecutorCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ExecutorCourseServiceImpl implements ExecutorCourseService {
    private final CourseMapper courseMapper;
    private final StudentCourseViewMapper studentCourseViewMapper;
    private final ExcelService excelService;

    @Autowired
    public ExecutorCourseServiceImpl(CourseMapper courseMapper,
                                     StudentCourseViewMapper studentCourseViewMapper,
                                     ExcelService excelService) {
        this.courseMapper = courseMapper;
        this.studentCourseViewMapper = studentCourseViewMapper;
        this.excelService = excelService;
    }

    @Override
    public Boolean addCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    @Override
    public Boolean deleteCourse(Integer courseId) {
        return courseMapper.deleteCourseByCourseId(courseId);
    }

    @Override
    public Boolean updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public InputStream getAllCourseStudentEmail(Integer courseId) {
        List<StudentCourse> students = studentCourseViewMapper.getStudentCourseByCourseId(courseId);
        return excelService.generateStudentEmailExcel(students);
    }
}
