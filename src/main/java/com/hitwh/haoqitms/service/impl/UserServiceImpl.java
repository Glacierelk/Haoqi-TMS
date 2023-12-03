package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.User;
import com.hitwh.haoqitms.mapper.EmployeeMapper;
import com.hitwh.haoqitms.mapper.UserMapper;
import com.hitwh.haoqitms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, EmployeeMapper employeeMapper) {
        this.userMapper = userMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // 0 经理
            // 1 执行人
            // 2 教师
            // 3 现场工作人员
            // 4 管理员
            // 5 学员
            if (user.getUserType() == 0) {
                user.setUserType(employeeMapper.selectEmployeeTypeById(user.getUserEmployeeId()));
            } else {
                user.setUserType(5);
            }
            return user;
        }
        return null;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }
}
