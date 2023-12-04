package com.hitwh.haoqitms.controller.executor;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.service.executor.ExecutorStudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequestMapping("/executor/student")
@RestController
public class ExecutorStudentController {
    private final ExecutorStudentService executorStudentService;

    @Autowired
    public ExecutorStudentController(ExecutorStudentService executorStudentService) {
        this.executorStudentService = executorStudentService;
    }

    /**
     * 导出学生邮箱
     * @return 学生邮箱的excel文件
     */
    @GetMapping("/email/download")
    public ResponseEntity<InputStreamResource> downloadEmail(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        try {
            String encodedFileName = URLEncoder.encode("学生邮箱.xlsx", StandardCharsets.UTF_8);
            headers.add("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
        } catch (Exception e) {
            headers.add("Content-Disposition", "attachment; filename=course_student_emails.xlsx");
        }

        InputStream inputStream = executorStudentService.getAllStudentEmail();
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .headers(headers)
                .body(inputStreamResource);
    }

    /**
     * 导入学生信息
     * @param file 学生信息的excel文件
     * @return 是否导入成功
     */
    @PostMapping("/import")
    public ResultInfo importStudent(@RequestParam("students") MultipartFile file){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(executorStudentService.importStudent(file));
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg(e.getMessage());
        }
        return resultInfo;
    }

    /**
     * 下载学员批量导入模板
     *
     * @return 学员批量导入模板
     */
    @GetMapping("/template")
    public ResponseEntity<InputStreamResource> downloadTemplate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        try {
            String encodedFileName = URLEncoder.encode("学生信息模板.xlsx", StandardCharsets.UTF_8);
            headers.add("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
        } catch (Exception e) {
            headers.add("Content-Disposition", "attachment; filename=students_template.xlsx");
        }

        try {
            InputStream inputStream = executorStudentService.getTemplate();
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(inputStreamResource);
        } catch (Exception e) {
            return ResponseEntity.notFound().headers(headers).build();
        }
    }

    /**
     * 导出学生信息
     * @return 学生信息的excel文件
     */
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportStudent(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        try {
            String encodedFileName = URLEncoder.encode("学生信息.xlsx", StandardCharsets.UTF_8);
            headers.add("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
        } catch (Exception e) {
            headers.add("Content-Disposition", "attachment; filename=students.xlsx");
        }

        InputStream inputStream = executorStudentService.exportStudent();
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .headers(headers)
                .body(inputStreamResource);
    }

    /**
     * 创建学员(前端首先要判断学员填报的团报码和公司名称是否合法)
     * @param request
     * @param student
     * @return
     */
    @PostMapping("/create")
    public ResultInfo create(HttpServletRequest request, @RequestBody Student student){
        ResultInfo info = new ResultInfo();
        try {
            Boolean result = executorStudentService.createStudent(student);
            if (result) {
                info.setFlag(true);
            } else {
                info.setFlag(false);
                info.setErrorMsg("创建失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("创建失败");
        }
        return info;
    }

    /**
     * 获取所有学员的信息
     * @param request
     * @return
     */
    @GetMapping("/getAll")
    public ResultInfo getAll(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        try {
            List<Student> students = executorStudentService.getAllStudents();
            info.setFlag(true);
            info.setData(students);
        }catch (Exception e){
            e.printStackTrace();
            info.setFlag(false);
            info.setErrorMsg("fail to get all infos");
        }
        return info;
    }

    /**
     * 更新学生信息
     * @param student
     */
    @PostMapping("/update")
    public ResultInfo updateStudent(@RequestBody Student student){
        ResultInfo resultInfo = new ResultInfo();
        if(executorStudentService.updateStudent(student)){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("fail to update");
        }
        return  resultInfo;
    }

    @GetMapping("/delete")
    public ResultInfo delete(HttpServletRequest request,@RequestParam  Integer studentId){
        ResultInfo info = new ResultInfo();
        if(executorStudentService.deleteStudent(studentId)){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("fail to delete student");
        }
        return info;
    }

}
