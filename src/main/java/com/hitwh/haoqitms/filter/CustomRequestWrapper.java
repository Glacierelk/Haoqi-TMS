package com.hitwh.haoqitms.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class CustomRequestWrapper extends HttpServletRequestWrapper {
    private final String decodedQueryString;

    public CustomRequestWrapper(HttpServletRequest request, String decodedQueryString) {
        super(request);
        this.decodedQueryString = decodedQueryString;
    }

    @Override
    public String getQueryString() {
        return decodedQueryString;
    }
}
