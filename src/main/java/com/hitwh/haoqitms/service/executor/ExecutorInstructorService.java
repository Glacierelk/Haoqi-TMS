package com.hitwh.haoqitms.service.executor;

import com.hitwh.haoqitms.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public interface ExecutorInstructorService {
    /**
     * 插入讲师信息
     * */
    Boolean insertInstructor(Employee Instructor);
    /**
     * 删除讲师信息
     * */
    Boolean deleteInstructor(Integer id);
    /**
     * 勇敢姓名进行讲师的模糊查询
     * */
    List<Employee> searchByName(String name);
    /**
     * 更新讲师信息
     * */
    Boolean update(Employee employee);

    /**
     *  获取所有教师信息
     * */
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

    /**
     * 导出教师信息
     * @return 教师信息的excel文件流
     */
    InputStream exportInstructor();
}
