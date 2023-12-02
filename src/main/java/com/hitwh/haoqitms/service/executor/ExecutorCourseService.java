package com.hitwh.haoqitms.service.executor;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.Pagination;

import java.io.InputStream;

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

    /**
     * 获取课程所有学员的邮箱
     *
     * @param courseId 课程id
     * @return 所有学员的邮箱
     */
    InputStream getAllCourseStudentEmail(Integer courseId);

    /**
     * 获取课程列表
     *
     * @param courseName 课程名
     * @param pageSize 每页大小
     * @param currentPage 当前页
     * @return 课程列表
     */
    Pagination getCourseList(String courseName, Integer pageSize, Integer currentPage);
}
