package com.hitwh.haoqitms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CourseMapper {
    /**
     * 根据课程id获取课程调查报告 [现场工作人员]
     * @param courseId 课程id
     * @return 课程调查报告
     */
    @Select("select report from course where course_id = #{courseId}")
    String getReportByCourseId(int courseId);

    /**
     * 根据课程id更新课程调查报告 [现场工作人员]
     * @param courseId 课程id
     * @param report 课程调查报告
     * @return 是否更新成功
     */
    @Update("update course set report = #{report} where course_id = #{courseId}")
    Boolean updateReportByCourseId(int courseId, String report);
}
