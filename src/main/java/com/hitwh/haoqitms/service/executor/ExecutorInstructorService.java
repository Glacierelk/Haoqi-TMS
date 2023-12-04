package com.hitwh.haoqitms.service.executor;

import com.hitwh.haoqitms.entity.Employee;

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
}
