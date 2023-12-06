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

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExecutorCourseServiceImpl implements ExecutorCourseService {
    private final ExcelService excelService;
    private final CourseMapper courseMapper;
    private final EmployeeMapper employeeMapper;
    private final StudentCourseViewMapper studentCourseViewMapper;
    private final TrainingApplicationMapper trainingApplicationMapper;

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
            Integer backPatchCourseId = course.getCourseId();
            System.out.println(backPatchCourseId);
            if(trainingApplicationMapper.backPatchCourseId(course.getApplicationId(), backPatchCourseId)) {
                // 将培训申请状态置为3（已完成）
                return trainingApplicationMapper.finishApplication(course.getApplicationId()) && courseMapper.initRevenue(backPatchCourseId);
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

    @Override
    public InputStream courseNoticeHTML(Integer courseId) {
        // 读入模板文件
        try (InputStream inputStream = new FileInputStream("src/main/resources/templates/notice.html")) {
            // 读入模板文件
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String html = new String(bytes);
            CourseList course = courseMapper.getCourseByCourseId(courseId);

            // 替换模板中的变量
            html = html.replace("Course Name", course.getName());
            html = html.replace("Course Instructor", course.getInstructorName());
            html = html.replace("Location", course.getLocation());
            html = html.replace("Company", course.getCompanyName());
            html = html.replace("Start Date", dataFormat(course.getStartDate()));
            html = html.replace("End Date", dataFormat(course.getEndDate()));
            html = html.replace("Course Fee", course.getCourseFee().toString());
            html = html.replace("Course Description", course.getDescription());

            // 将替换后的html写入到输入流中
            return new ByteArrayInputStream(html.getBytes());
        } catch (IOException e) {
            return null;
        }
    }

    private String dataFormat(String date) {
        // 将字符串解析为 LocalDateTime 对象
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 定义所需的日期格式
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 格式化 LocalDateTime 对象为只包含日期的格式
        return dateTime.format(dateFormatter);
    }
}
