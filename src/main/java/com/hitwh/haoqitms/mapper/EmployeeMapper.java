package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE username = #{username}")
    Employee selectIdByUsername(String username);

    @Insert("INSERT INTO employee(name, username, password, employee_type) " +
            "VALUES(#{name}, #{username}, #{password}, #{employeeType})")
    Boolean createEmployee(Employee employee);

    @Delete("DELETE FROM employee WHERE employee_id=#{id}")
    Boolean deleteEmployee(Integer id);

    @Select("SELECT * FROM employee WHERE name LIKE '%${name}%'")
    List<Employee> searchByName(String name);

    @Update("UPDATE employee SET "+
            "name=#{name},"+
            "contact_info=#{contactInfo},"+
            "email=#{email},"+
            "title=#{title},"+
            "expertise_area=#{expertiseArea} "+
            "WHERE employee_id=#{employeeId}")
    Boolean update(Employee Instructor);

    /**
     * 根据id查询员工信息 [login]
     * @param id 员工id
     * @return 员工信息
     */
    @Select("SELECT employee_type FROM employee WHERE employee_id=#{id}")
    Integer selectEmployeeTypeById(Integer id);

    /**
     * 获取执行人数量 [manager]
     *
     * @return 执行人数量
     */
    @Select("SELECT COUNT(*) FROM employee WHERE employee_type=1")
    Integer selectExecutorCount();

    /**
     * 获取执行人概要信息 [manager]
     *
     * @param pageSize    页面大小
     * @param offset     偏移量
     * @return 执行人概要信息
     */
    @Select("SELECT employee_id, name FROM employee WHERE employee_type=1 LIMIT #{pageSize} OFFSET #{offset}")
    List<Employee> selectExecutorSummary(Integer pageSize, Integer offset);
  
    @Insert("INSERT INTO employee(name, username, password, contact_info,email,title,expertise_area,employee_type) " +
            "VALUES(#{name}, #{username}, #{password}, #{contactInfo}, #{email}, #{title}, #{expertiseArea}, #{employeeType})")
    Boolean addEmployee(Employee employee);

    @Select("SELECT * FROM employee WHERE employee_type=2")
    List<Employee> getAllTeachers();

    @Select("SELECT COUNT(*) FROM employee WHERE employee_type = 2")
    Integer countTotalInstructor();

    @Select("SELECT * FROM employee WHERE employee_type=1")
    List<Employee> getAllExecutors();

    @Select("select * from employee where employee_type = 2 and employee_id = #{employee_id}")
    Employee getAllYourInfo(Integer employee_id);
}
