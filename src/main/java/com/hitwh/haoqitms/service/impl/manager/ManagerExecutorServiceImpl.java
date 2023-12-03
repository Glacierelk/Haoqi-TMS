package com.hitwh.haoqitms.service.impl.manager;

import com.hitwh.haoqitms.entity.Employee;
import com.hitwh.haoqitms.entity.ExecutorSummary;
import com.hitwh.haoqitms.entity.Pagination;
import com.hitwh.haoqitms.mapper.CourseMapper;
import com.hitwh.haoqitms.mapper.EmployeeMapper;
import com.hitwh.haoqitms.service.manager.ManagerExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerExecutorServiceImpl implements ManagerExecutorService {
    private final EmployeeMapper employeeMapper;
    private final CourseMapper courseMapper;

    @Autowired
    public ManagerExecutorServiceImpl(EmployeeMapper employeeMapper, CourseMapper courseMapper) {
        this.employeeMapper = employeeMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    public Integer getExecutorCount() {
        return employeeMapper.selectExecutorCount();
    }

    @Override
    public Pagination getExecutorSummary(Integer pageSize, Integer currentPage) {
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setCurrentPage(currentPage);
        pagination.setTotal(employeeMapper.selectExecutorCount());

        List<ExecutorSummary> list = new ArrayList<>();
        List<Employee> employees = employeeMapper.selectExecutorSummary(pageSize, (currentPage - 1) * pageSize);
        for (Employee employee : employees) {
            ExecutorSummary executorSummary = new ExecutorSummary();
            executorSummary.setExecutorId(employee.getEmployeeId());
            executorSummary.setExecutorName(employee.getName());
            executorSummary.setCourseCount(courseMapper.getCourseCountByExecutorId(employee.getEmployeeId()));
            list.add(executorSummary);
        }
        pagination.setData(list);

        return pagination;
    }
}
