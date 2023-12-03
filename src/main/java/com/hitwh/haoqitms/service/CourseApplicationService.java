package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.CourseApplication;

public interface CourseApplicationService {
    /**
     * 新建课程申请
     * @param courseApplication 课程申请
     * @return 是否新建成功
     */
    Boolean createCourseApplication(CourseApplication courseApplication);
}
