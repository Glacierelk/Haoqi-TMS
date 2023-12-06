package com.hitwh.haoqitms.service.impl.executor;

import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.mapper.StudentMapper;
import com.hitwh.haoqitms.service.ExcelService;
import com.hitwh.haoqitms.service.executor.ExecutorStudentService;
import com.hitwh.haoqitms.utils.UserNameAndPassWordGenerator;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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
    @Transactional(rollbackFor = Exception.class)
    public Boolean importStudent(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.endsWith(".xlsx")) {
            try {
                DataFormatter formatter = new DataFormatter();
                Workbook workbook = WorkbookFactory.create(file.getInputStream());
                Sheet sheet = workbook.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 2; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null || row.getCell(6) == null) {
                        throw new RuntimeException("必填项不能为空");
                    }

                    Student student = new Student();
                    student.setName(formatter.formatCellValue(row.getCell(0)));
                    student.setGender(Objects.equals(formatter.formatCellValue(row.getCell(1)), "男") ? 0 : 1);
                    student.setEmail(formatter.formatCellValue(row.getCell(2)));
                    student.setCompanyName(formatter.formatCellValue(row.getCell(3)));
                    student.setJobPosition(formatter.formatCellValue(row.getCell(4)));
                    student.setSkillLevel(formatter.formatCellValue(row.getCell(5)));
                    student.setContactInfo(formatter.formatCellValue(row.getCell(6)));
                    student.setUsername(UserNameAndPassWordGenerator.generateUserName(student.getEmail(), student.getContactInfo()));
                    student.setPassword(UserNameAndPassWordGenerator.generatePassWord(student.getEmail(), student.getContactInfo()));
                    System.out.println(student);
                    try {
                        studentMapper.createStudent(student);
                    } catch (Exception e) {
//                        e.printStackTrace();
                        throw new SQLException(e.getMessage());
                    }
                }
            } catch (Exception e) {
                if (e.getMessage().equals("必填项不能为空")) {
                    throw new RuntimeException(e.getMessage());
                } else if (e.getMessage().equals("导入失败")) {
                    throw new RuntimeException(e.getMessage());
                } else if (e instanceof SQLException) {
                    throw new RuntimeException("导入失败，存在重复的邮箱或联系方式");
                } else {
                    throw new RuntimeException("导入失败");
                }
            }
            return true;
        } else {
            throw new RuntimeException("不是有效的 Excel 文件");
        }
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

    @Override
    public InputStream exportStudent() {
        return excelService.generateStudentInfoExcel(studentMapper.getAllStudentAllInfo());
    }

    @Override
    public InputStream getTemplate() throws FileNotFoundException {
        return new FileInputStream("src/main/resources/templates/student_import.xlsx");
    }

}
