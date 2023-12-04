package com.hitwh.haoqitms.controller;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.User;
import com.hitwh.haoqitms.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
     * @param user 用户信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResultInfo login(@RequestBody User user) {
        User existUser = userService.login(user.getUsername(), user.getPassword());
        ResultInfo info = new ResultInfo();
        if (null != existUser) {
//            HttpSession session = request.getSession();
//            session.setAttribute("user", existUser);
//            System.out.println("existUser = " + existUser);
//            System.out.println("session = " + session.getAttribute("user"));
            info.setFlag(true);
            info.setData(existUser);
//            info.setErrorMsg(generateJWT(existUser));
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
            System.out.println("user = " + user);
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

    private String generateJWT(User user) {
        long expirationTimeInMs = TimeUnit.HOURS.toMillis(1); // 令牌有效期为1小时
        String secretKey = "YourSecretKey"; // 应该是一个安全的随机字符串，用于签名JWT

        // 设置JWT的payload
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserType() == 1 ? user.getUserStudentId() : user.getUserEmployeeId());
        claims.put("username", user.getUsername());
        // 可以根据需要添加更多的用户信息到payload中

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
