package com.hitwh.haoqitms.controller.executor;

import com.hitwh.haoqitms.service.executor.ExecutorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequestMapping("/executor/student")
@RestController
public class ExecutorStudentController {
    private final ExecutorStudentService executorStudentService;

    @Autowired
    public ExecutorStudentController(ExecutorStudentService executorStudentService) {
        this.executorStudentService = executorStudentService;
    }

    @GetMapping("/email")
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
}
