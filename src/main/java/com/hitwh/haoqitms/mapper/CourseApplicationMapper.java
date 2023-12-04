package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.CourseApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface CourseApplicationMapper {
    /**
     * 新建课程申请
     * @param courseApplication 课程申请
     * @return 是否新建成功
     */
    @Insert("insert into course_application " +
            "(course_id, course_name, name, gender, email, company_name, job_position, skill_level, contact_info, status) " +
            "values(#{courseId}, #{courseName}, #{name}, #{gender}, #{email}, #{companyName}, #{jobPosition}, #{skillLevel}, #{contactInfo}, #{status})")
    Boolean createCourseApplication(CourseApplication courseApplication);

    /**
     * 获取所有课程申请信息
     */
    @Select("select * from course_application")
    List<CourseApplication> getAllCourseApplication();

    /**
     * 通过学员课程申请
     */
    @Update("update course_application set status = 1 where application_id = #{applicationId}")
    Boolean approveApplication(Integer applicationId);

    /**
     * 拒绝学员课程申请
     */
    @Update("update course_application set status = 2 where application_id = #{applicationId}")
    Boolean rejectApplication(Integer applicationId);

    /**
     * 根据申请表id获取申请表信息
     */
    @Select("select * from course_application where application_id = #{applicationId}")
    CourseApplication getCourseApplicationById(Integer applicationId);

    /**
     * 根据申请表id回填学生id
     * @param applicationId 申请表id
     * @param studentId 学生id
     */
    @Update("update course_application set student_id = #{studentId} where application_id = #{applicationId}")
    Boolean backPatchStudentId(Integer applicationId, Integer studentId);

    /**
     * 获取团报申请的学生列表
     * @param courseId 课程编号
     * @return 学生列表
     */
    @Select("select student_id from course_application where course_id = #{courseId} and promo_code is not null")
    List<Integer> getPromotedCourseApplications(Integer courseId);

}
