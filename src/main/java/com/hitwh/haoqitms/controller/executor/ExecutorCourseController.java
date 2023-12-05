package com.hitwh.haoqitms.controller.executor;

import com.hitwh.haoqitms.entity.Course;
import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.executor.ExecutorCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequestMapping("/executor/course")
@RestController
public class ExecutorCourseController {
    private final ExecutorCourseService executorCourseService;

    @Autowired
    public ExecutorCourseController(ExecutorCourseService executorCourseService) {
        this.executorCourseService = executorCourseService;
    }

    /**
     * 获取所有课程信息
     *
     * @return 所有课程信息
     */
    @GetMapping("/list/{name}/{page_size}/{current_page}/{user_id}")
    public ResultInfo getCourses(@PathVariable("name") String name,
                                 @PathVariable("page_size") Integer pageSize,
                                 @PathVariable("current_page") Integer currentPage,
                                 @PathVariable("user_id") Integer userId){
        name = name.equals("null") ? null : name;

        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(executorCourseService.getCourseList(name, pageSize, currentPage, userId));
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取课程列表失败");
        }
        return resultInfo;
    }


    /**
     * 创建课程
     *
     * @param course 课程信息
     * @return 创建结果
     */
    @PostMapping("/add")
    public ResultInfo addCourse(@RequestBody Course course){
        ResultInfo resultInfo = new ResultInfo();
        if (executorCourseService.addCourse(course)){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("创建失败!");
        }
        return  resultInfo;
    }

    /**
     * 删除课程
     *
     * @param id 课程id
     * @return 删除结果
     */
    @DeleteMapping("/delete/{course_id}")
    public ResultInfo deleteCourse(@PathVariable("course_id") Integer id){
        ResultInfo resultInfo = new ResultInfo();
        if(executorCourseService.deleteCourse(id)){
            resultInfo.setFlag(true);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("删除失败!");
        }
        return resultInfo;
    }

    /**
     * 更新课程信息
     *
     * @param course 课程信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public ResultInfo updateCourse(@RequestBody Course course){
        ResultInfo resultInfo = new ResultInfo();
        if(executorCourseService.updateCourse(course)){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("更新失败!");
        }
        return resultInfo;
    }

    /**
     * 获取课程所有学员的邮箱
     * @param courseId 课程id
     * @return 所有学员的邮箱
     */
    @GetMapping("/email/{course_id}")
    public ResponseEntity<InputStreamResource> downloadEmail(@PathVariable("course_id") Integer courseId){
        System.out.println("downloadEmail" + courseId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        try {
            String encodedFileName = URLEncoder.encode("学生邮箱.xlsx", StandardCharsets.UTF_8);
            headers.add("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
        } catch (Exception e) {
            headers.add("Content-Disposition", "attachment; filename=course_student_emails.xlsx");
        }

        InputStream inputStream = executorCourseService.getAllCourseStudentEmail(courseId);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .headers(headers)
                .body(inputStreamResource);
    }

    /**
     * 获取已通过的软件公司申请审批
     * @return 已通过的软件公司申请审批
     */
    @GetMapping("/allApprovedTrainingApplication")
    public ResultInfo getAllApprovedTrainingApplication(){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(executorCourseService.getAllApprovedTrainingApplication());
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取已通过的软件公司申请审批失败");
        }
        return resultInfo;
    }

    /**
     * 根据培训申请新建课程
     * @param course 课程信息
     */
    @PostMapping("/createCourse")
    public ResultInfo createCourse(@RequestBody Course course){
          ResultInfo resultInfo = new ResultInfo();
          try {
              boolean flag = executorCourseService.createCourse(course);
              if(flag){
                  resultInfo.setFlag(flag);
              }else{
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMsg("根据培训申请新建课程失败");
              }
          } catch (Exception e) {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("error");
          }
          return resultInfo;
    }

    /**
     * 获取所有讲师信息
     * @return 所有讲师信息
     */
    @GetMapping("/allInstructor")
    public ResultInfo getAllInstructor(){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(executorCourseService.getAllInstructor());
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取所有讲师信息失败");
        }
        return resultInfo;
    }

    /**
     * 获取所有执行人
     * @return 所有执行人
     */
    @GetMapping("/allExecutor")
    public ResultInfo getAllExecutor(){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(executorCourseService.getAllExecutor());
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取所有执行人失败");
        }
        return resultInfo;
    }

    /**
     * 获取课程通知HTML
     * @param courseId 课程id
     * @return 课程通知HTML
     */
    @GetMapping("/notice/{course_id}")
    public ResponseEntity<InputStreamResource> downloadNotice(@PathVariable("course_id") Integer courseId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/html"));
        try {
            String encodedFileName = URLEncoder.encode("课程通知.html", StandardCharsets.UTF_8);
            headers.add("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
        } catch (Exception e) {
            headers.add("Content-Disposition", "attachment; filename=course_notice.html");
        }

        InputStream inputStream = executorCourseService.courseNoticeHTML(courseId);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .headers(headers)
                .body(inputStreamResource);
    }

}
