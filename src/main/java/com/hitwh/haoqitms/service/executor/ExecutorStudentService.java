package com.hitwh.haoqitms.service.executor;

import com.hitwh.haoqitms.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

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

    /**
     * 创建学员
     * @param student
     * @return
     */
    Boolean createStudent(Student student);

    /**
     * 查询学员
     * @return student info
     */
    public List<Student> getAllStudents();

    /**
     * 更新学员信息学员
     * @return student info
     */
    public Boolean updateStudent(Student student);

    /**
     * 删除学生信息
     * @param studentId
     * */
    public Boolean deleteStudent(Integer studentId);

    /**
     * 导出学生信息
     * @return 学生信息的excel文件
     */
    InputStream exportStudent();

    /**
     * 获取学生信息模板
     * @return 学生信息模板
     */
    InputStream getTemplate() throws FileNotFoundException;
}
