package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.TrainingApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrainingApplicationMapper {
    @Insert("INSERT INTO training_application(company_name, promo_code, budget, contact_info, status) " +
            "VALUES(#{companyName}, #{promoCode}, #{budget}, #{contactInfo}, #{status})")
    Boolean createTrainingApplication(TrainingApplication trainingApplication);

    @Insert("SELECT company_name FROM training_application WHERE promo_code = #{promoCode}")
    String selectCompanyNameByPromoCode(String promoCode);

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
}
