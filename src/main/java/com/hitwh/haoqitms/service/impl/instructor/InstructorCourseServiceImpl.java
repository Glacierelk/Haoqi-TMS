package com.hitwh.haoqitms.service.impl.instructor;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.mapper.CourseMapper;
import com.hitwh.haoqitms.service.instructor.InstructorCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorCourseServiceImpl implements InstructorCourseService {
    private final CourseMapper courseMapper;

    @Autowired
    public InstructorCourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    /**
     * 获取所有课程信息
     * @param employee_id
     * @return
     */
    @Override
    public List<Course> getAllYourCourse(Integer employee_id){

        return courseMapper.getAllYourCourse(employee_id);
    }
}
