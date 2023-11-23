package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.CourseList;
import com.hitwh.haoqitms.entity.Pagination;
import com.hitwh.haoqitms.mapper.CourseMapper;
import com.hitwh.haoqitms.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {
    private final CourseMapper courseMapper;

    @Autowired
    public HomeServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public Pagination getCourses(CourseList courseList, Integer pageSize, Integer currentPage) {
        return null;
    }
}
