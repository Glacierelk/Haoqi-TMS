package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.Course;

public interface ExecutorCourseService {
    /**
     * 添加课程
     *
     * @param course 课程信息
     * @return 是否添加成功
     */
    Boolean addCourse(Course course);

    /**
     * 删除课程
     *
     * @param courseId 课程id
     * @return 是否删除成功
     */
    Boolean deleteCourse(Integer courseId);

    /**
     * 修改课程
     *
     * @param course 课程信息
     * @return 是否修改成功
     */
    Boolean updateCourse(Course course);
}
