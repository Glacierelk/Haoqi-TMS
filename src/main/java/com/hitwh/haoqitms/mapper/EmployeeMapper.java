package com.hitwh.haoqitms.mapper;

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
}
