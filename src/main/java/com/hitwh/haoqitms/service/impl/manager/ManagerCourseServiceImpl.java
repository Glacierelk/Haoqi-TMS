package com.hitwh.haoqitms.service.impl.manager;

import com.hitwh.haoqitms.entity.*;
import com.hitwh.haoqitms.mapper.*;
import com.hitwh.haoqitms.service.manager.ManagerCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerCourseServiceImpl implements ManagerCourseService {
    private final CourseMapper courseMapper;
    private final TrainingEvaluationMapper trainingEvaluationMapper;

    @Autowired
    public ManagerCourseServiceImpl(CourseMapper courseMapper, TrainingEvaluationMapper trainingEvaluationMapper) {
        this.courseMapper = courseMapper;
        this.trainingEvaluationMapper = trainingEvaluationMapper;
    }

    @Override
    public Integer getCoursesCount() {
        return courseMapper.getCourseTotalCount();
    }

    @Override
    public Double getCourseTotalRevenue() {
        return courseMapper.getCourseTotalRevenue();
    }

    @Override
    public Pagination getCoursesList(Integer pageSize, Integer currentPage) {
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setCurrentPage(currentPage);
        pagination.setTotal(courseMapper.getCourseTotalCount());

        List<CourseSummary> courses = new ArrayList<>();
        List<CourseList> allCourses = courseMapper.getCourses(new CourseList(), pageSize, (currentPage - 1) * pageSize);
        for (CourseList courseList : allCourses) {
            CourseSummary courseSummary = new CourseSummary();
            courseSummary.setCourseId(courseList.getCourseId());
            courseSummary.setName(courseList.getName());
            courseSummary.setRevenue(courseList.getRevenue());
            courseSummary.setStatus(getStatus(courseList.getStartDate(), courseList.getEndDate()));
            courseSummary.setAverageSatisfactionLevel(calcAverageSatisfactionLevel(courseList.getCourseId()));
            courseSummary.setReport(courseList.getReport());
            courses.add(courseSummary);
        }
        pagination.setData(courses);

        return pagination;
    }

    /**
     * 计算平均满意度
     * @param courseId 课程id
     * @return 平均满意度
     */
    private Double calcAverageSatisfactionLevel(Integer courseId) {
        List<TrainingEvaluation> trainingEvaluationByCourseId = trainingEvaluationMapper.getTrainingEvaluationByCourseId(courseId);
        double sum = 0.0;
        for (TrainingEvaluation trainingEvaluation : trainingEvaluationByCourseId) {
            sum += trainingEvaluation.getSatisfactionLevel();
        }
        if (trainingEvaluationByCourseId.isEmpty()) {
            return 0.0;
        }
        return sum / trainingEvaluationByCourseId.size();
    }

    /**
     * 计算课程状态
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 课程状态
     */
    private Integer getStatus(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date currentDate = new Date();
            Date startDateParsed = sdf.parse(startDate);
            Date endDateParsed = sdf.parse(endDate);

            if (currentDate.after(endDateParsed)) {
                return 0; // 已结束
            } else if (currentDate.before(startDateParsed)) {
                return 2; // 未开始
            } else {
                return 1; // 正在进行
            }
        } catch (ParseException e) {
            return 0;
        }
    }
}
