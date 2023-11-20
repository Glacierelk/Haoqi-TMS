package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    User login(String username, String password);

    /**
     * 获取用户列表
     * @return 用户列表
     */
    List<User> getUserList();
}
