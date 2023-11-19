package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(String username);

    @Insert("INSERT INTO user(user_id, username, password, user_type) VALUES(#{userId}, #{username}, #{password}, #{userType})")
    Integer insert(User user);
}
