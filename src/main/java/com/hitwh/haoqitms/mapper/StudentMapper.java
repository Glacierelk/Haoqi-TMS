package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
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
     * 新增学员选课记录
     */


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
    @Update("UPDATE student SET "+
            "name=#{name},"+
            "contact_info=#{contactInfo},"+
            "email=#{email},"+
            "job_position=#{jobPosition},"+
            "skill_level=#{skillLevel},"+
            "company_name=#{companyName},"+
            "gender=#{gender} "+
            "WHERE student_id=#{studentId}")
    Boolean updateStudent(Student student);

    @Delete("DELETE FROM student WHERE student_id=#{studentId}")
    Boolean deleteStudent(Integer studentId);


}
