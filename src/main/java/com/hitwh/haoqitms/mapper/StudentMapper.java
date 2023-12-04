package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.Student;

import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface StudentMapper {
    @Insert("INSERT INTO student(name, gender, username, password, email, company_name, job_position, skill_level, contact_info) " +
            "VALUES(#{name}, #{gender}, #{username}, #{password}, #{email}, #{companyName}, #{jobPosition}, #{skillLevel}, #{contactInfo})")
    @Options(useGeneratedKeys = true, keyProperty = "studentId", keyColumn = "student_id")
    Boolean createStudent(Student student);

    /**
     * 获取所有学生邮箱 [执行人]
     *
     * @return 所有学生邮箱
     */
    @Select("SELECT name, email FROM student")
    List<Student> getAllStudent();

    /**
     * 根据邮箱和手机号查询学生
     */
    @Select("SELECT * FROM student WHERE email = #{email} AND contact_info = #{contactInfo}")
    Student getStudentByEmailAndPhone(String email, String contactInfo);

    /**
     * 获取所有学生的所有信息
     *
     * @return 所有学生的所有信息
     */
    @Select("SELECT * FROM student")
    List<Student> getAllStudentAllInfo();

    /**
     * 修改学生的信息
     */
    @Update("UPDATE student SET " +
            "name=#{name}," +
            "contact_info=#{contactInfo}," +
            "email=#{email}," +
            "job_position=#{jobPosition}," +
            "skill_level=#{skillLevel}," +
            "company_name=#{companyName}," +
            "gender=#{gender} " +
            "WHERE student_id=#{studentId}")
    Boolean updateStudent(Student student);

    @Delete("DELETE FROM student WHERE student_id=#{studentId}")
    Boolean deleteStudent(Integer studentId);

    /**
     * 查询学生的总数
     *
     * @return 学生的总数
     */
    @Select("SELECT COUNT(*) FROM student")
    Integer countTotalStudent();

    /**
     * 根据学生id查询课程列表
     *
     * @param studentId 学生id
     * @return 课程列表
     */
    @Select("SELECT\n" +
            "    c.course_id,\n" +
            "    c.name AS name,\n" +
            "    e.name AS instructor_name,\n" +
            "    c.start_date,\n" +
            "    c.end_date,\n" +
            "    sc.is_evaluated\n" +
            "FROM\n" +
            "    student_course sc\n" +
            "        JOIN\n" +
            "    course c ON sc.course_id = c.course_id\n" +
            "        LEFT JOIN\n" +
            "    employee e ON c.instructor_id = e.employee_id\n" +
            "        LEFT JOIN\n" +
            "    training_evaluation te ON sc.student_id = te.student_id AND sc.course_id = te.course_id\n" +
            "WHERE\n" +
            "        sc.student_id = #{studentId};")
    List<Course> getCourseListByStudentId(Integer studentId);

    /**
     * 根据学员id和课程id查询学员是否签到或付款
     */
    @Select("SELECT * FROM student_course WHERE student_id=#{studentId} AND course_id=#{courseId} AND attendance=1 AND paid=1 AND is_evaluated=0")
    Student checkAttendenceAndPaid(Integer studentId, Integer courseId);

}
