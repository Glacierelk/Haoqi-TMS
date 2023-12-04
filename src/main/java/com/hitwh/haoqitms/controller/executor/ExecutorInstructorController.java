package com.hitwh.haoqitms.controller.executor;

import com.hitwh.haoqitms.entity.Employee;
import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.executor.ExecutorInstructorService;
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

@RequestMapping("/executor/Instructor")
@RestController
public class ExecutorInstructorController {
    private final ExecutorInstructorService executorInstructorService;

    @Autowired
    public ExecutorInstructorController(ExecutorInstructorService executorEmployeeService) {
        this.executorInstructorService = executorEmployeeService;
    }


    @PostMapping("/add")
    public ResultInfo addInstructor(@RequestBody Employee instructor){
        ResultInfo resultInfo = new ResultInfo();
        if (executorInstructorService.insertInstructor(instructor)){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("fail to insert!");
        }
        return  resultInfo;
    }

    @GetMapping("/delete")
    public ResultInfo deleteInstructor(Integer id){
        ResultInfo resultInfo = new ResultInfo();
        if(executorInstructorService.deleteInstructor(id)){
            resultInfo.setFlag(true);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("fail to delete!");
        }
        return resultInfo;
    }

    @GetMapping("/search")
    public List<Employee> searchInstructor(String name){
        return executorInstructorService.searchByName(name);
    }

    @PostMapping("/update")
    public ResultInfo updateInstructor(@RequestBody Employee instructor){
        ResultInfo resultInfo = new ResultInfo();
        if(executorInstructorService.update(instructor)){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("fail to update");
        }
        return  resultInfo;
    }

    @GetMapping("/getAllTeachers")
    public List<Employee> getAllTeachers(){
        return executorInstructorService.getAllTeachers();
    }

    /**
     * 下载讲师批量导入模板
     *
     * @return excel文件流
     */
    @GetMapping("/importTemplate")
    public ResponseEntity<InputStreamResource> downloadTemplate(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        try {
            String encodedFileName = URLEncoder.encode("讲师批量导入模板.xlsx", StandardCharsets.UTF_8);
            headers.add("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
        } catch (Exception e) {
            headers.add("Content-Disposition", "attachment; filename=instructor_import_template.xlsx");
        }

        try {
            InputStream inputStream = executorInstructorService.getTemplate();
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(inputStreamResource);
        } catch (Exception e) {
            return ResponseEntity.notFound().headers(headers).build();
        }
    }

    /**
     * 导入讲师信息
     * @param file 讲师信息的excel文件
     * @return 是否导入成功
     */
    @PostMapping("/import")
    public ResultInfo importInstructor(@RequestParam("instructors") MultipartFile file){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(executorInstructorService.importInstructor(file));
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg(e.getMessage());
        }
        return resultInfo;
    }

}
