package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.CourseApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseApplicationMapper {
    /**
     * 新建课程申请
     * @param courseApplication 课程申请
     * @return 是否新建成功
     */
    @Insert("insert into course_application " +
            "(course_id, name, gender, email, company_name, job_position, skill_level, contact_info) " +
            "values(#{courseId}, #{name}, #{gender}, #{email}, #{companyName}, #{jobPosition}, #{skillLevel}, #{contactInfo})")
    Boolean createCourseApplication(CourseApplication courseApplication);

    /**
     * 获取团报申请的学生列表
     * @param courseId 课程编号
     * @return 学生列表
     */
    @Select("select student_id from course_application where course_id = #{courseId} and promo_code is not null")
    List<Integer> getPromotedCourseApplications(Integer courseId);
}
