package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.Pagination;
import com.hitwh.haoqitms.entity.StudentCourse;
import com.hitwh.haoqitms.entity.TrainingEvaluation;

import java.util.List;

public interface StaffService {
    /**
     * 根据课程id获取培训评价, 用于现场工作人员完成培训调查报告
     * @param courseId 课程id
     * @return 培训评价列表
     */
    List<TrainingEvaluation> getTrainingEvaluationByCourseId(Integer courseId);
    Pagination getTrainingEvaluationByCourseId(Integer courseId, Integer pageSize, Integer currentPage);

    /**
     * 根据课程id等信息获取学生信息, 用于现场工作人员完成学生身份核实、考勤和缴费
     * @param studentCourse 搜索条件
     * @return 学生信息列表
     */
    Integer getStudentsCount(StudentCourse studentCourse);
    List<StudentCourse> getStudents(StudentCourse studentCourse, Integer pageSize, Integer currentPage);

    /**
     * 根据学生id和课程id更新学生缴费状态
     * @param studentId 学生id
     * @param courseId 课程id
     * @return 是否更新成功
     */
    Boolean updateStudentPayStatus(Integer studentId, Integer courseId);

    /**
     * 根据学生id和课程id更新学生考勤状态
     * @param studentId 学生id
     * @param courseId 课程id
     * @param attendance 考勤状态
     * @return 是否更新成功
     */
    Boolean updateStudentAttendanceStatus(Integer studentId, Integer courseId, Boolean attendance);

    /**
     * 根据课程id 添加或更新 课程调查报告
     * @param courseId 课程id
     * @param report 课程调查报告
     * @return 是否更新成功
     */
    Boolean updateCourseSurveyReport(Integer courseId, String report);

    /**
     * 根据课程id获取课程调查报告
     * @param courseId 课程id
     * @return 课程调查报告
     */
    String getCourseSurveyReport(Integer courseId);

    /**
     * 根据评价id删除评价
     * @param evaluationId 评价id
     * @return 是否删除成功
     */
    Boolean deleteTrainingEvaluation(Integer evaluationId);
}
