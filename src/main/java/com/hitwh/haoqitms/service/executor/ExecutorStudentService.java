package com.hitwh.haoqitms.service.executor;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ExecutorStudentService {
    /**
     * 获取所有学生的邮箱
     *
     * @return 所有学生的邮箱表格流
     */
    InputStream getAllStudentEmail();

    /**
     * 导入学生信息
     *
     * @param file 学生信息表格
     * @return 是否导入成功
     */
    Boolean importStudent(MultipartFile file);
}
