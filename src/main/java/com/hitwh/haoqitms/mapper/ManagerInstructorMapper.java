package com.hitwh.haoqitms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManagerInstructorMapper {
    @Select("SELECT COUNT(*) FROM employee WHERE employee_type = 2")
    Integer countTotalInstructor();
}
