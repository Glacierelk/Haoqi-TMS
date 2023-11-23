package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.CourseList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    /**
     * 获取课程列表 [首页]
     * @param courseList 课程列表
     * @param pageSize 每页大小
     * @param offset 偏移量
     * @return 课程列表
     */
    List<CourseList> getCourses(CourseList courseList, Integer pageSize, Integer offset);
    Integer getCoursesCount(CourseList courseList);
}
