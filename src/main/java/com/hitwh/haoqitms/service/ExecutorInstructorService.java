package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.Employee;

import java.util.List;

public interface ExecutorInstructorService {
    Boolean insertInstructor(Employee Instructor);
    Boolean deleteInstructor(Integer id);
    List<Employee> searchByName(String name);
    Boolean update(Employee employee);
}
