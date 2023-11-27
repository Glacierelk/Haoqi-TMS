package com.hitwh.haoqitms.service.executor;

import java.io.InputStream;

public interface ExecutorStudentService {
    /**
     * 获取所有学生的邮箱
     *
     * @return 所有学生的邮箱表格流
     */
    InputStream getAllStudentEmail();
}
