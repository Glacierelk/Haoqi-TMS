package com.hitwh.haoqitms.service;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.TrainingEvaluation;

import java.util.List;

public interface StudentService {
    /**
     * 创建培训评价[学员]
     * @param trainingEvaluation
     * @return
     */
    Boolean createTrainingEvaluation(TrainingEvaluation trainingEvaluation);

    /**
     * 根据学生id查询课程列表
     * @param studentId
     * @return 课程列表
     */
    List<Course> getCourseListByStudentId(Integer studentId);
}
