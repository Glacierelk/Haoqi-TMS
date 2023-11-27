package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Insert("INSERT INTO student(name, gender, email, company_name, job_position, skill_level, contact_info) " +
            "VALUES(#{name}, #{gender}, #{email}, #{companyName}, #{jobPosition}, #{skillLevel}, #{contactInfo})")
    Boolean createStudent(Student student);

    /**
     * 获取所有学生邮箱 [执行人]
     *
     * @return 所有学生邮箱
     */
    @Select("SELECT name, email FROM student")
    List<Student> getAllStudent();
}
