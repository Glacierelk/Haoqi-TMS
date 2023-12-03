package com.hitwh.haoqitms.controller.manager;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.manager.ManagerCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager/course")
public class ManagerCourseSummaryController {
    private final ManagerCourseService managerCourseService;

    @Autowired
    public ManagerCourseSummaryController(ManagerCourseService managerCourseService) {
        this.managerCourseService = managerCourseService;
    }

    /**
     * 获取课程总数
     * @return 课程总数
     */
    @GetMapping("/count")
    public ResultInfo getCourseCount(){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(managerCourseService.getCoursesCount());
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取课程总数失败");
        }
        return resultInfo;
    }

    /**
     * 获取课程总收入
     * @return 课程总收入
     */
    @GetMapping("/revenue")
    public ResultInfo getCourseTotalRevenue(){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(managerCourseService.getCourseTotalRevenue());
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取课程总收入失败");
        }
        return resultInfo;
    }

    /**
     * 获取课程列表
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 课程列表
     */
    @GetMapping("/list/{page_size}/{current_path}")
    public ResultInfo getCoursesList(@PathVariable("page_size") Integer pageSize,
                                     @PathVariable("current_path") Integer currentPage){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(managerCourseService.getCoursesList(pageSize, currentPage));
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取课程列表失败");
        }
        return resultInfo;
    }

}
