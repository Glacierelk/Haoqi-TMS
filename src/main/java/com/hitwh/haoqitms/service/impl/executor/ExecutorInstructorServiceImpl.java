package com.hitwh.haoqitms.service.impl.executor;

import com.hitwh.haoqitms.entity.Employee;
import com.hitwh.haoqitms.mapper.EmployeeMapper;
import com.hitwh.haoqitms.service.executor.ExecutorInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Service
public class ExecutorInstructorServiceImpl implements ExecutorInstructorService {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public ExecutorInstructorServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Boolean insertInstructor(Employee instructor){
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
}
