package com.hitwh.haoqitms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManagerStudentMapper {
    @Select("SELECT COUNT(*) FROM student")
    Integer countTotalStudent();
}
