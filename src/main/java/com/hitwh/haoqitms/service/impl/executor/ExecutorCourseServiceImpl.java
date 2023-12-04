package com.hitwh.haoqitms.service.impl.executor;

import com.hitwh.haoqitms.entity.*;
import com.hitwh.haoqitms.mapper.CourseMapper;
import com.hitwh.haoqitms.mapper.EmployeeMapper;
import com.hitwh.haoqitms.mapper.StudentCourseViewMapper;
import com.hitwh.haoqitms.mapper.TrainingApplicationMapper;
import com.hitwh.haoqitms.service.ExcelService;
import com.hitwh.haoqitms.service.executor.ExecutorCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ExecutorCourseServiceImpl implements ExecutorCourseService {
    private final CourseMapper courseMapper;
    private final StudentCourseViewMapper studentCourseViewMapper;
    private final ExcelService excelService;
    private final TrainingApplicationMapper trainingApplicationMapper;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public ExecutorCourseServiceImpl(CourseMapper courseMapper,
                                     StudentCourseViewMapper studentCourseViewMapper,
                                     ExcelService excelService, TrainingApplicationMapper trainingApplicationMapper,
                                     EmployeeMapper employeeMapper) {
        this.courseMapper = courseMapper;
        this.studentCourseViewMapper = studentCourseViewMapper;
        this.excelService = excelService;
        this.trainingApplicationMapper = trainingApplicationMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Boolean addCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    @Override
    public Boolean deleteCourse(Integer courseId) {
        return courseMapper.deleteCourseByCourseId(courseId);
    }

    @Override
    public Boolean updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public InputStream getAllCourseStudentEmail(Integer courseId) {
        List<StudentCourse> students = studentCourseViewMapper.getStudentCourseByCourseId(courseId);
        return excelService.generateStudentEmailExcel(students);
    }

    @Override
    public Pagination getCourseList(String courseName, Integer pageSize, Integer currentPage) {
        System.out.println(courseName);
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setCurrentPage(currentPage);
        pagination.setTotal(courseMapper.getCourseCountByName(courseName));
        pagination.setData(courseMapper.getCourseByName(courseName, pageSize, (currentPage - 1) * pageSize));
        return pagination;
    }

    @Override
    public List<TrainingApplication> getAllApprovedTrainingApplication() {
        return trainingApplicationMapper.getAllAcceptedTrainingApplication();
    }

    @Override
    public Boolean createCourse(Course course) {
        if(courseMapper.insertCourse(course)){
            // 回填课程id
            if(trainingApplicationMapper.backPatchCourseId(course.getApplicationId(), course.getCourseId())) {
                // 将培训申请状态置为3（已完成）
                return trainingApplicationMapper.finishApplication(course.getApplicationId());
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Employee> getAllInstructor() {
        return employeeMapper.getAllTeachers();
    }

    @Override
    public List<Employee> getAllExecutor() {
        return employeeMapper.getAllExecutors();
    }
}
