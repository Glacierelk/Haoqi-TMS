package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);
    List<User> getUserList();
}
