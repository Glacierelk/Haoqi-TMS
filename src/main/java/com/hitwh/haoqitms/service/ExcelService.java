package com.hitwh.haoqitms.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface ExcelService {
    /**
     * 生成学员邮箱excel
     * @param emails 学员邮箱
     * @return excel文件流
     */
    InputStream generateStudentEmailExcel(List<?> emails);

    /**
     * 读取excel文件
     * @param file 文件
     * @param clazz 类
     * @return 读取结果
     */
    List<?> readExcel(MultipartFile file, Class<?> clazz);
}
