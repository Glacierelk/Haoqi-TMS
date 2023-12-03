package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.User;
import com.hitwh.haoqitms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     * @param request 请求
     * @param user 用户信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResultInfo login(HttpServletRequest request, @RequestBody User user) {
        User existUser = userService.login(user.getUsername(), user.getPassword());
        ResultInfo info = new ResultInfo();
        if (null != existUser) {
            HttpSession session = request.getSession();
            session.setAttribute("user", existUser);
            info.setFlag(true);
            info.setData(existUser);
        } else {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        return info;
    }

    /**
     * 用户退出
     * @param request 请求
     * @return 退出结果
     */
    @GetMapping("/logout")
    public ResultInfo logout(HttpServletRequest request) {
        ResultInfo info = new ResultInfo();
        try {
            request.getSession().invalidate();
            info.setFlag(true);
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("注销失败");
        }
        return info;
    }

    /**
     * 检查用户是否登录
     *
     * @param request 请求
     * @return 检查结果
     */
    @GetMapping("/checkLogin")
    public ResultInfo checkLogin(HttpServletRequest request) {
        ResultInfo info = new ResultInfo();
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                info.setFlag(true);
                info.setData(user);
            } else {
                info.setFlag(false);
                info.setErrorMsg("未登录");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("未登录");
        }
        return info;
    }
}
