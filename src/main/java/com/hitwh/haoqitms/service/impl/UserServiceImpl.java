package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.User;
import com.hitwh.haoqitms.mapper.UserMapper;
import com.hitwh.haoqitms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }
}
