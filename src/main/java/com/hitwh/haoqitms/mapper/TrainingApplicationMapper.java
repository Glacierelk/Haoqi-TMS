package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.TrainingApplication;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainingApplicationMapper {
    @Insert("INSERT INTO training_application(company_name, promo_code, budget, contact_info, status) " +
            "VALUES(#{companyName}, #{promoCode}, #{budget}, #{contactInfo}, #{status})")
    Boolean createTrainingApplication(TrainingApplication trainingApplication);

    /**
     * 根据团报码查询公司名称
     * @param promoCode 团报码
     * @return 公司名称
     */
    @Select("SELECT company_name FROM training_application WHERE promo_code = #{promoCode}")
    String selectCompanyNameByPromoCode(String promoCode);

    /**
     * 根据课程id查询团报码
     * @param courseId 课程id
     * @return 团报码
     */
    @Select("SELECT promo_code FROM training_application WHERE course_id = #{courseId}")
    String selectPromoCodeByCourseId(Integer courseId);

    /**
     * 根据条件查询培训申请 [经理]
     * @param trainingApplication 查询条件
     * @param pageSize 分页大小
     * @param offset 偏移量
     * @return 培训申请列表
     */
    List<TrainingApplication> selectTrainingApplications(@Param("trainingApplication") TrainingApplication trainingApplication,
                                                         @Param("pageSize") Integer pageSize,
                                                         @Param("offset") Integer offset);
    Integer selectTrainingApplicationsCount(@Param("trainingApplication") TrainingApplication trainingApplication);

    /**
     * 接受培训申请 [经理]
     * @param applicationId 培训申请id
     * @return 是否成功
     */
    @Update("UPDATE training_application SET status = 1 WHERE application_id = #{applicationId}")
    Boolean acceptApplication(Integer applicationId);

    /**
     * 拒绝培训申请 [经理]
     * @param applicationId 培训申请id
     * @return 是否成功
     */
    @Update("UPDATE training_application SET status = 2 WHERE application_id = #{applicationId}")
    Boolean rejectApplication(Integer applicationId);

    /**
     * 查询所有公司名称 [提交报名表界面]
     * @param
     * @return
     */
    @Select("SELECT company_name FROM training_application GROUP BY company_name")
    List<String> selectAllCompanyName();

    /**
     * 根据课程ID查询预算 [经理]
     * @param courseId 课程ID
     * @return 预算
     */
    @Select("SELECT budget FROM training_application WHERE course_id = #{courseId}")
    Double getBudgetByCourseId(Integer courseId);

    /**
     * 获取所有已通过的培训申请 [执行人]
     * @param
     * @return 培训申请列表
     */
    @Select("SELECT * FROM training_application WHERE status = 1")
    List<TrainingApplication> getAllAcceptedTrainingApplication();

    /**
     * 根据课程表id回填课程id
     * @param applicationId 申请表id
     * @param courseId 课程id
     * return 是否成功
     */
    @Update("UPDATE training_application SET course_id = #{courseId} WHERE application_id = #{applicationId}")
    Boolean backPatchCourseId(Integer applicationId, Integer courseId);

    /**
     * 完成培训申请
     * @param applicationId 申请表id
     * return 是否成功
     */
    @Update("UPDATE training_application SET status = 3 WHERE application_id = #{applicationId}")
    Boolean finishApplication(Integer applicationId);
}
