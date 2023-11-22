package com.hitwh.haoqitms.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求方法
//        String method = request.getMethod();
        //解决post请求中文数据乱码问题
//        if (method.equalsIgnoreCase("post")) {
//            request.setCharacterEncoding("utf-8");
//        }
        //处理响应乱码
//        response.setContentType("text/html;charset=utf-8");

        //本地部署打开这段
        String clientOrigin = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", clientOrigin);
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        //服务器部署打开这段
//        response.setHeader("Access-Control-Allow-Origin", "*");

        //这些默认加上
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "DNT,X-Custom-Header,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            String queryString = ((HttpServletRequest) servletRequest).getQueryString();
            if (queryString != null) {
                String decodedQueryString = URLDecoder.decode(queryString, StandardCharsets.UTF_8);
                // 你可以在这里做一些操作，比如修改解码后的参数值，然后继续处理请求
                filterChain.doFilter(new CustomRequestWrapper(request, decodedQueryString), response);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}