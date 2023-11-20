package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {
    private final UserMapper userMapper;

    @Autowired
    public UserMapperTest(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    void selectByUsername() {
        User user = userMapper.selectByUsername("2021210666");
        System.out.println(user);
    }
}