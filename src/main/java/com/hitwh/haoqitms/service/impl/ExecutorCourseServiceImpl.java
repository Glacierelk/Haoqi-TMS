package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.mapper.CourseMapper;
import com.hitwh.haoqitms.service.ExecutorCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutorCourseServiceImpl implements ExecutorCourseService {
    private final CourseMapper courseMapper;

    @Autowired
    public ExecutorCourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
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
}
