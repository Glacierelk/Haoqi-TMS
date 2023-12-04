package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.CourseList;
import com.hitwh.haoqitms.entity.Pagination;
import com.hitwh.haoqitms.entity.TrainingApplication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeService {
    /**
     * 创建培训申请
     * @param trainingApplication
     * @return
     */
    Boolean createTrainingApplication(TrainingApplication trainingApplication);

    /**
     * 根据团报码查询公司名称
     * @param promoCode
     * @return
     */
    String selectCompanyNameByPromoCode(String promoCode);

    /**
     * 根据课程id查询团报码
     * @param courseId
     * @return 团报码
     */
    String selectPromoCodeByCourseId(Integer courseId);

    /**
     * 查询培训申请
     * @param trainingApplication 查询条件
     * @param pageSize 每页大小
     * @param currentPage 当前页
     * @return 分页对象
     */
    Pagination selectTrainingApplications(TrainingApplication trainingApplication, Integer pageSize, Integer currentPage);

    /**
     * 拒绝培训申请
     * @param applicationId 培训申请id
     * @return 是否成功
     */
    Boolean acceptApplication(Integer applicationId);

    /**
     * 接受培训申请
     * @param applicationId 培训申请id
     * @return 是否成功
     */
    Boolean rejectApplication(Integer applicationId);

    /**
     * 查询所有公司名称
     * @param
     * @return
     */
    List<String> selectAllCompanyName();

    /**
     * 首页获取课程列表
     * @param courseList 查询条件
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 课程列表
     */
    Pagination getCourses(CourseList courseList, Integer pageSize, Integer currentPage);
}
