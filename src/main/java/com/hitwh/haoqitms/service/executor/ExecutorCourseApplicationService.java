package com.hitwh.haoqitms.service.executor;

import com.hitwh.haoqitms.entity.CourseApplication;

import java.util.List;

public interface ExecutorCourseApplicationService {
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
     * 根据执行人id获取课程申请信息
     * @param executorId 执行人id
     * @return 课程申请信息
     */
    List<CourseApplication> getCourseApplicationByExecutorId(Integer executorId);

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
