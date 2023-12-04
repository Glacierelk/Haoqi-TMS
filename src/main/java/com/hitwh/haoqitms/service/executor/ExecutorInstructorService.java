package com.hitwh.haoqitms.service.executor;

import com.hitwh.haoqitms.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public interface ExecutorInstructorService {
    Boolean insertInstructor(Employee Instructor);
    Boolean deleteInstructor(Integer id);
    List<Employee> searchByName(String name);
    Boolean update(Employee employee);

    List<Employee> getAllTeachers();

    /**
     * 获取导出模板
     * @return 模板文件流
     */
    InputStream getTemplate() throws FileNotFoundException;

    /**
     * 批量导入教师信息
     * @param file 教师信息的excel文件
     * @return 是否导入成功
     */
    Boolean importInstructor(MultipartFile file);
}
