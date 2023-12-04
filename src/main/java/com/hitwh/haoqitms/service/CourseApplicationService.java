package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.CourseApplication;
import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.entity.StudentCourse;

import java.util.List;

public interface CourseApplicationService {
    /**
     * 新建课程申请
     *
     * @param courseApplication 课程申请
     * @return 是否新建成功
     */
    Boolean createCourseApplication(CourseApplication courseApplication);

    /**
     * 获取所有课程申请信息
     */
    List<CourseApplication> getAllCourseApplication();

    /**
     * 通过学员课程申请
     *  @param applicationId 申请id
     */
    Boolean approveApplication(Integer applicationId);

    /**
     * 拒绝学员课程申请
     * @param applicationId 申请id
     */
    Boolean rejectApplication(Integer applicationId);
}
