package com.hitwh.haoqitms.service.impl.executor;

import com.hitwh.haoqitms.entity.Employee;
import com.hitwh.haoqitms.mapper.EmployeeMapper;
import com.hitwh.haoqitms.service.ExcelService;
import com.hitwh.haoqitms.service.executor.ExecutorInstructorService;
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

@Service
public class ExecutorInstructorServiceImpl implements ExecutorInstructorService {
    private final EmployeeMapper employeeMapper;
    private final ExcelService excelService;

    @Autowired
    public ExecutorInstructorServiceImpl(EmployeeMapper employeeMapper, ExcelService excelService) {
        this.employeeMapper = employeeMapper;
        this.excelService = excelService;
    }

    @Override
    public Boolean insertInstructor(Employee instructor){
        instructor.setUsername(UserNameAndPassWordGenerator.generateUserName(instructor.getEmail(),instructor.getContactInfo()));
        instructor.setPassword(UserNameAndPassWordGenerator.generatePassWord(instructor.getEmail(),instructor.getContactInfo()));
        return employeeMapper.addEmployee(instructor);
    }

    @Override
    public Boolean deleteInstructor(Integer id) {
        return employeeMapper.deleteEmployee(id);
    }

    @Override
    public List<Employee> searchByName(String name) {
        return employeeMapper.searchByName(name);
    }

    @Override
    public Boolean update(Employee employee) {
        return employeeMapper.update(employee);
    }

    @Override
    public List<Employee> getAllTeachers() {
        return employeeMapper.getAllTeachers();
    }

    @Override
    public InputStream getTemplate() throws FileNotFoundException {
        return new FileInputStream("src/main/resources/templates/instructor_import.xlsx");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importInstructor(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.endsWith(".xlsx")) {
            try {
                DataFormatter dataFormatter = new DataFormatter();
                InputStream inputStream = file.getInputStream();
                Workbook workbook = WorkbookFactory.create(inputStream);
                Sheet sheet = workbook.getSheetAt(0);
                int rowNum = sheet.getLastRowNum();
                for (int i = 2; i <= rowNum; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null) {
                        throw new Exception("必填项不能为空");
                    }

                    Employee instructor = new Employee();
                    instructor.setName(dataFormatter.formatCellValue(row.getCell(0)));
                    instructor.setContactInfo(dataFormatter.formatCellValue(row.getCell(1)));
                    instructor.setEmail(dataFormatter.formatCellValue(row.getCell(2)));
                    instructor.setTitle(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
                    instructor.setExpertiseArea(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
                    instructor.setEmployeeType(2);
                    instructor.setUsername(UserNameAndPassWordGenerator.generateUserName(instructor.getEmail(), instructor.getContactInfo()));
                    instructor.setPassword(UserNameAndPassWordGenerator.generatePassWord(instructor.getEmail(), instructor.getContactInfo()));
//                    System.out.println(instructor);
                    try {
                        insertInstructor(instructor);
                    } catch (Exception e) {
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
    public InputStream exportInstructor() {
        return excelService.generateInstructorInfoExcel(employeeMapper.getAllInstructors());
    }
}
