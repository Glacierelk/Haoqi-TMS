package com.hitwh.haoqitms.service.impl.executor;

import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.mapper.StudentMapper;
import com.hitwh.haoqitms.service.ExcelService;
import com.hitwh.haoqitms.service.executor.ExecutorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class ExecutorStudentServiceImpl implements ExecutorStudentService {
    private final StudentMapper studentMapper;
    private final ExcelService excelService;

    @Autowired
    public ExecutorStudentServiceImpl(StudentMapper studentMapper, ExcelService excelService) {
        this.studentMapper = studentMapper;
        this.excelService = excelService;
    }

    @Override
    public InputStream getAllStudentEmail() {
        List<Student> students = studentMapper.getAllStudent();
        return excelService.generateStudentEmailExcel(students);
    }

    @Override
    public Boolean importStudent(MultipartFile file) {
        return null;
    }

    @Override
    public Boolean createStudent(Student student) {
        return studentMapper.createStudent(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.getAllStudentAllInfo();
    }

    @Override
    public Boolean updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public Boolean deleteStudent(Integer studentId) {
        return studentMapper.deleteStudent(studentId);
    }

}
