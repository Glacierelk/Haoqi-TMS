package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.StudentCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentCourseViewMapper {
    /**
     * 根据课程id获取学生信息, 用于现场工作人员完成学生身份核实、考勤和缴费
     *
     * @param studentCourse 查询内容
     * @return 选修该课程的学生信息列表
     */
    Integer getStudentCourseCount(@Param("studentCourse") StudentCourse studentCourse);
    List<StudentCourse> getStudentCourse(@Param("studentCourse") StudentCourse studentCourse,
                                                   @Param("pageSize") int pageSize,
                                                   @Param("offset") int offset);
    @Select("select * from student_course_view where course_id = #{courseId}")
    List<StudentCourse> getStudentCourseByCourseId(@Param("courseId") Integer courseId);

    /**
     * 根据学生id和课程id更新学生缴费状态
     *
     * @param studentId 学生id
     * @param courseId  课程id
     * @return 是否更新成功
     */
    @Update("update student_course_view set paid = true " +
            "where student_id = #{studentId} and course_id = #{courseId}")
    Boolean updateStudentPayStatus(Integer studentId, Integer courseId);

    /**
     * 根据学生id和课程id更新学生考勤状态
     *
     * @param studentId  学生id
     * @param courseId   课程id
     * @param attendance 考勤状态
     * @return 是否更新成功
     */
    @Update("update student_course_view set attendance = #{attendance} " +
            "where student_id = #{studentId} and course_id = #{courseId}")
    Boolean updateStudentAttendanceStatus(Integer studentId, Integer courseId, Boolean attendance);

    /**
     * 新增学员选课记录
     *
     * @param studentCourse 学员选课记录
     * @return 是否新增成功
     */
    @Insert("insert into student_course(student_id, course_id, paid, attendance) " +
            "values(#{studentId}, #{courseId}, #{paid}, #{attendance})")
    Boolean createStudentCourse(StudentCourse studentCourse);

    /**
     * 根据课程id获取已缴费学生信息
     *
     * @param courseId 课程id
     * @return 已缴费学生信息列表
     */
    @Select("select * from student_course_view where course_id = #{courseId} and paid = true")
    List<StudentCourse> getPaidStudentsByCourseId(Integer courseId);


}
