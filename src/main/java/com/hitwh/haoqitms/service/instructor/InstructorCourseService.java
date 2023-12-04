package com.hitwh.haoqitms.service.instructor;

import com.hitwh.haoqitms.entity.Course;

import java.util.List;

public interface InstructorCourseService {

    /**
     * 查询讲师课程
     *
     * @param employee_id
     * @return
     */
    List<Course> getAllYourCourse(Integer employee_id);
}
