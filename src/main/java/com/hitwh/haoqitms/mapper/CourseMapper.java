package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.CourseList;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    /**
     * 根据课程id获取课程调查报告 [现场工作人员]
     *
     * @param courseId 课程id
     * @return 课程调查报告
     */
    @Select("select report from course where course_id = #{courseId}")
    String getReportByCourseId(int courseId);

    /**
     * 根据课程id更新课程调查报告 [现场工作人员]
     *
     * @param courseId 课程id
     * @param report   课程调查报告
     * @return 是否更新成功
     */
    @Update("update course set report = #{report} where course_id = #{courseId}")
    Boolean updateReportByCourseId(int courseId, String report);

    /**
     * 获取课程列表 [首页]
     *
     * @param courseList 课程列表
     * @param pageSize   每页大小
     * @param offset     偏移量
     * @return 课程列表
     */
    List<CourseList> getCourses(@Param("courseList") CourseList courseList,
                                @Param("pageSize") Integer pageSize,
                                @Param("offset") Integer offset);

    Integer getCoursesCount(@Param("courseList") CourseList courseList);

    /**
     * 添加新的课程 [执行人]
     *
     * @param course 课程
     * @return 是否添加成功
     */
    @Insert("insert into course(name, company_name, description, start_date, end_date, location, course_fee, instructor_id, executor_id) " +
            "values(#{name}, #{companyName}, #{description}, #{startDate}, #{endDate}, #{location}, #{courseFee}, #{instructorId}, #{executorId})")
    Boolean insertCourse(Course course);

    /**
     * 根据课程id删除课程 [执行人]
     *
     * @param courseId 课程id
     * @return 是否删除成功
     */
    @Delete("delete from course where course_id = #{courseId}")
    Boolean deleteCourseByCourseId(int courseId);

    /**
     * 根据课程id更新课程 [执行人]
     *
     * @param course 课程
     * @return 是否更新成功
     */
    @Update("update course set name = #{name}, company_name = #{companyName}, description = #{description}, start_date = #{startDate}, " +
            "end_date = #{endDate}, location = #{location}, course_fee = #{courseFee}, instructor_id = #{instructorId}, executor_id = #{executorId} " +
            "where course_id = #{courseId}")
    Boolean updateCourse(Course course);

    /**
     * 根据课程名称查询课程 [执行人]
     *
     * @param name 课程名称
     * @param pageSize 每页大小
     * @param offset 偏移量
     * @return 课程列表
     */
    List<Course> getCourseByName(@Param("name") String name,
                                 @Param("pageSize") Integer pageSize,
                                 @Param("offset") Integer offset);
    Integer getCourseCountByName(@Param("name") String name);

}
