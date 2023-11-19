package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    private final UserMapper userMapper;

    @Autowired
    public UserMapperTest(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    void selectByUsername() {

    }

    @Test
    void insert() {
        User user = new User();
        user.setUserId(0);
        user.setUsername("admin");
        user.setPassword("123456");
        user.setUserType(5);
        System.out.println(userMapper.insert(user));
    }
}