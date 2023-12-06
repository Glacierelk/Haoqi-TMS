package com.hitwh.haoqitms.service.impl.executor;

import com.hitwh.haoqitms.entity.CourseApplication;
import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.entity.StudentCourse;
import com.hitwh.haoqitms.mapper.CourseApplicationMapper;
import com.hitwh.haoqitms.mapper.StudentCourseViewMapper;
import com.hitwh.haoqitms.mapper.StudentMapper;
import com.hitwh.haoqitms.service.executor.ExecutorCourseApplicationService;
import com.hitwh.haoqitms.utils.UserNameAndPassWordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutorCourseApplicationServiceImpl implements ExecutorCourseApplicationService {
    private final CourseApplicationMapper courseApplicationMapper;
    private final StudentMapper studentMapper;
    private final StudentCourseViewMapper studentCourseViewMapper;

    @Autowired
    public ExecutorCourseApplicationServiceImpl(CourseApplicationMapper courseApplicationMapper, StudentMapper studentMapper, StudentCourseViewMapper studentCourseViewMapper) {
        this.courseApplicationMapper = courseApplicationMapper;
        this.studentMapper = studentMapper;
        this.studentCourseViewMapper = studentCourseViewMapper;
    }

    @Override
    public Boolean createCourseApplication(CourseApplication courseApplication) {
        return courseApplicationMapper.createCourseApplication(courseApplication);
    }

    @Override
    public List<CourseApplication> getAllCourseApplication() {
        return courseApplicationMapper.getAllCourseApplication();
    }

    @Override
    public List<CourseApplication> getCourseApplicationByExecutorId(Integer executorId) {

        return courseApplicationMapper.getCourseApplicationByExecutorId(executorId);
    }

    @Override
    public Boolean approveApplication(Integer applicationId) {
        // 修改申请表状态(已通过)
        if (courseApplicationMapper.approveApplication(applicationId)) {
            // 根据申请表id获取申请表信息
            CourseApplication courseApplication = courseApplicationMapper.getCourseApplicationById(applicationId);
            // 根据邮箱和手机号查询学生
            Student student = studentMapper.getStudentByEmailAndPhone(courseApplication.getEmail(), courseApplication.getContactInfo());
            Integer backPatchStudentId = null;
            if (null == student) {
                // 新增学员选课记录
                Student newStudent = new Student();
                newStudent.setName(courseApplication.getName());
                newStudent.setUsername(UserNameAndPassWordGenerator.generateUserName(courseApplication.getEmail(), courseApplication.getContactInfo()));
                newStudent.setPassword(UserNameAndPassWordGenerator.generatePassWord(courseApplication.getEmail(), courseApplication.getContactInfo()));
                newStudent.setGender(courseApplication.getGender());
                newStudent.setEmail(courseApplication.getEmail());
                newStudent.setCompanyName(courseApplication.getCompanyName());
                newStudent.setJobPosition(courseApplication.getJobPosition());
                newStudent.setSkillLevel(courseApplication.getSkillLevel());
                newStudent.setContactInfo(courseApplication.getContactInfo());

                // 新增学员
                Boolean flagCreateStudent = studentMapper.createStudent(newStudent);
                if (flagCreateStudent) {
                    backPatchStudentId = newStudent.getStudentId();
                }else {
                    return false;
                }
            } else {
                // 已有该学生
                backPatchStudentId = student.getStudentId();
            }
            // 回填
            if(courseApplicationMapper.backPatchStudentId(applicationId, backPatchStudentId)){
                // 插入SC表记录
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudentId(backPatchStudentId);
                studentCourse.setCourseId(courseApplication.getCourseId());
                studentCourse.setAttendance(false);
                studentCourse.setPaid(false);
                studentCourse.setIsEvaluated(false);
                // 检查是否是提交申请的软件公司的员工（有团报码）
                if (null != courseApplication.getPromoCode()) {
                    studentCourse.setPaid(true);
                }
                return studentCourseViewMapper.createStudentCourse(studentCourse);

            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean rejectApplication(Integer applicationId) {
        return courseApplicationMapper.rejectApplication(applicationId);
    }

}