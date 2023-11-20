package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.TrainingApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrainingApplicationMapper {
    @Insert("INSERT INTO training_application(company_name, promo_code, budget, contact_info, status) " +
            "VALUES(#{companyName}, #{promoCode}, #{budget}, #{contactInfo}, #{status})")
    Boolean createTrainingApplication(TrainingApplication trainingApplication);

    @Insert("SELECT company_name FROM training_application WHERE promo_code = #{promoCode}")
    String selectCompanyNameByPromoCode(String promoCode);
}
