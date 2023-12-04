package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.Employee;

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
     * 生成讲师信息excel
     * @param instructors 讲师信息
     * @return excel文件流
     */
    InputStream generateInstructorInfoExcel(List<Employee> instructors);
}
