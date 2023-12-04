package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.entity.TrainingEvaluation;
import com.hitwh.haoqitms.mapper.StudentCourseViewMapper;
import com.hitwh.haoqitms.mapper.StudentMapper;
import com.hitwh.haoqitms.mapper.TrainingEvaluationMapper;
import com.hitwh.haoqitms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final TrainingEvaluationMapper trainingEvaluationMapper;
    private final StudentMapper studentMapper;
    private final StudentCourseViewMapper studentCourseViewMapper;

    @Autowired
    public StudentServiceImpl(TrainingEvaluationMapper trainingEvaluationMapper, StudentMapper studentMapper, StudentCourseViewMapper studentCourseViewMapper) {
        this.trainingEvaluationMapper = trainingEvaluationMapper;
        this.studentMapper = studentMapper;
        this.studentCourseViewMapper = studentCourseViewMapper;
    }


    @Override
    public Boolean createTrainingEvaluation(TrainingEvaluation trainingEvaluation) {
        // 设置当前时间
        Student check = studentMapper.checkAttendenceAndPaid(trainingEvaluation.getStudentId(), trainingEvaluation.getCourseId());
        if (check == null) {
            return false;
        }
        return trainingEvaluationMapper.createTrainingEvaluation(trainingEvaluation) && studentCourseViewMapper.updateStudentEvaluationStatus(trainingEvaluation.getStudentId(), trainingEvaluation.getCourseId());
    }

    @Override
    public List<Course> getCourseListByStudentId(Integer studentId) {
        return studentMapper.getCourseListByStudentId(studentId);
    }
}
