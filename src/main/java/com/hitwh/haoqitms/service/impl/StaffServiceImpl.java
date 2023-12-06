package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.Pagination;
import com.hitwh.haoqitms.entity.StudentCourse;
import com.hitwh.haoqitms.entity.TrainingEvaluation;
import com.hitwh.haoqitms.mapper.CourseMapper;
import com.hitwh.haoqitms.mapper.StudentCourseViewMapper;
import com.hitwh.haoqitms.mapper.TrainingEvaluationMapper;
import com.hitwh.haoqitms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    private final CourseMapper courseMapper;
    private final StudentCourseViewMapper studentCourseViewMapper;
    private final TrainingEvaluationMapper trainingEvaluationMapper;

    @Autowired
    public StaffServiceImpl(CourseMapper courseMapper,
                            StudentCourseViewMapper studentCourseViewMapper,
                            TrainingEvaluationMapper trainingEvaluationMapper) {
        this.courseMapper = courseMapper;
        this.studentCourseViewMapper = studentCourseViewMapper;
        this.trainingEvaluationMapper = trainingEvaluationMapper;
    }

    @Override
    public List<TrainingEvaluation> getTrainingEvaluationByCourseId(Integer courseId) {
        return trainingEvaluationMapper.getTrainingEvaluationByCourseId(courseId);
    }

    @Override
    public Pagination getTrainingEvaluationByCourseId(Integer courseId,
                                                      Integer pageSize,
                                                      Integer currentPage) {
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setCurrentPage(currentPage);
        pagination.setData(trainingEvaluationMapper.getPaginationTrainingEvaluationByCourseId(courseId, pageSize, (currentPage - 1) * pageSize));
        pagination.setTotal(trainingEvaluationMapper.getTrainingEvaluationCountByCourseId(courseId));
        return pagination;
    }

    @Override
    public Integer getStudentsCount(StudentCourse studentCourse) {
        if (studentCourse.getName() != null) {
            studentCourse.setName("%" + studentCourse.getName() + "%");
        }
        if (studentCourse.getCompanyName() != null) {
            studentCourse.setCompanyName("%" + studentCourse.getCompanyName() + "%");
        }
        return studentCourseViewMapper.getStudentCourseCount(studentCourse);
    }

    @Override
    public List<StudentCourse> getStudents(StudentCourse studentCourse, Integer pageSize, Integer currentPage) {
        if (studentCourse.getName() != null) {
            studentCourse.setName("%" + studentCourse.getName() + "%");
        }
        if (studentCourse.getCompanyName() != null) {
            studentCourse.setCompanyName("%" + studentCourse.getCompanyName() + "%");
        }
        return studentCourseViewMapper.getStudentCourse(studentCourse, pageSize, (currentPage - 1) * pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStudentPayStatus(Integer studentId, Integer courseId) {
        Boolean flag = studentCourseViewMapper.updateStudentPayStatus(studentId, courseId);

        if (flag) {
            Double courseFee = courseMapper.getCourseFeeByCourseId(courseId);
            Double revenue = courseMapper.getRevenueByCourseId(courseId);
            if (courseFee == null) {
                courseFee = 0.0;
            }
            if (revenue == null) {
                revenue = 0.0;
            }
            try {
                return courseMapper.updateRevenueByCourseId(courseId, revenue + courseFee);
            } catch (Exception e) {
                throw new RuntimeException("更新课程收入失败");
            }
        } else {
            throw new RuntimeException("更新学员缴费状态失败");
        }
    }

    @Override
    public Boolean updateStudentAttendanceStatus(Integer studentId, Integer courseId, Boolean attendance) {
        return studentCourseViewMapper.updateStudentAttendanceStatus(studentId, courseId, attendance);
    }

    @Override
    public Boolean updateCourseSurveyReport(Integer courseId, String report) {
        return courseMapper.updateReportByCourseId(courseId, report);
    }

    @Override
    public String getCourseSurveyReport(Integer courseId) {
        return courseMapper.getReportByCourseId(courseId);
    }

    @Override
    public Boolean deleteTrainingEvaluation(Integer evaluationId) {
        return trainingEvaluationMapper.deleteTrainingEvaluation(evaluationId);
    }
}
