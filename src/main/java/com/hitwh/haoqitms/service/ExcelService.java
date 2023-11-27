package com.hitwh.haoqitms.service;

import java.io.InputStream;
import java.util.List;

public interface ExcelService {
    /**
     * 生成学员邮箱excel
     * @param emails 学员邮箱
     * @return excel文件流
     */
    InputStream generateStudentEmailExcel(List<?> emails);
}
