package com.hitwh.haoqitms.mapper;

import com.hitwh.haoqitms.entity.TrainingEvaluation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrainingEvaluationMapper {
    /**
     * 根据课程id获取课程评价 [现场工作人员]
     * @param courseId 课程id
     * @return 课程评价列表
     */
    @Select("select * from training_evaluation where course_id = #{courseId}")
    List<TrainingEvaluation> getTrainingEvaluationByCourseId(Integer courseId);
    @Select("select count(*) from training_evaluation where course_id = #{courseId}")
    Integer getTrainingEvaluationCountByCourseId(Integer courseId);
    @Select("select * from training_evaluation where course_id = #{courseId} limit #{pageSize} offset #{offset}")
    List<TrainingEvaluation> getPaginationTrainingEvaluationByCourseId(Integer courseId,
                                                            Integer pageSize,
                                                            Integer offset);

    /**
     * 根据评价id删除评价 [现场工作人员]
     * @param evaluationId 评价id
     * @return 是否删除成功
     */
    @Delete("delete from training_evaluation where evaluation_id = #{evaluationId}")
    Boolean deleteTrainingEvaluation(Integer evaluationId);

    /**
     * 创建培训评价 [学员]
     * @param trainingEvaluation
     * @return
     */
    @Insert("insert into training_evaluation(course_id, student_id, satisfaction_level, comments, evaluation_time) " +
            "values(#{courseId}, #{studentId}, #{satisfactionLevel}, #{comments}, #{evaluationTime})")
    Boolean createTrainingEvaluation(TrainingEvaluation trainingEvaluation);
}
