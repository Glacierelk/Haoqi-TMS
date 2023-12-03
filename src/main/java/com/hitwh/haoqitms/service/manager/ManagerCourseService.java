package com.hitwh.haoqitms.service.manager;

import com.hitwh.haoqitms.entity.Pagination;

public interface ManagerCourseService {
    /**
     * 获取课程总数
     * @return 课程总数
     */
    Integer getCoursesCount();

    /**
     * 获取课程汇总列表
     * @param pageSize 每页大小
     * @param currentPage 当前页码
     * @return 课程汇总列表
     */
    Pagination getCoursesList(Integer pageSize, Integer currentPage);

    /**
     * 获取课程总收入
     * @return 课程总收入
     */
    Double getCourseTotalRevenue();
}
