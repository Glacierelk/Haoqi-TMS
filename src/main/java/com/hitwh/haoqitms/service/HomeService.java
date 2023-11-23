package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.CourseList;
import com.hitwh.haoqitms.entity.Pagination;

public interface HomeService {
    /**
     * 首页获取课程列表
     * @param courseList 查询条件
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 课程列表
     */
    Pagination getCourses(CourseList courseList, Integer pageSize, Integer currentPage);
}
