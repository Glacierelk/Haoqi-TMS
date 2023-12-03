package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.CourseApplication;
import com.hitwh.haoqitms.mapper.CourseApplicationMapper;
import com.hitwh.haoqitms.service.CourseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseApplicationServiceImpl implements CourseApplicationService {
    private final CourseApplicationMapper courseApplicationMapper;

    @Autowired
    public CourseApplicationServiceImpl(CourseApplicationMapper courseApplicationMapper) {
        this.courseApplicationMapper = courseApplicationMapper;
    }

    @Override
    public Boolean createCourseApplication(CourseApplication courseApplication) {
        return courseApplicationMapper.createCourseApplication(courseApplication);
    }
}