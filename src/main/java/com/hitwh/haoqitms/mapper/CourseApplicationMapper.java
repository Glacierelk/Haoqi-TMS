package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.CourseApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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

}
