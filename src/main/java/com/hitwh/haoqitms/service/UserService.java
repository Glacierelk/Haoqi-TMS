package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User login(String username, String password);
    List<User> getUserList();
}
