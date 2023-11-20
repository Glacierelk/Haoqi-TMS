package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE username = #{username}")
    Employee selectIdByUsername(String username);

    @Insert("INSERT INTO employee(name, username, password, employee_type) " +
            "VALUES(#{name}, #{username}, #{password}, #{employeeType})")
    Boolean createEmployee(Employee employee);
}
