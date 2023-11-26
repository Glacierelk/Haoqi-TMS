package com.hitwh.haoqitms.controller;


import com.hitwh.haoqitms.entity.CourseList;
import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
public class HomeController {
    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    /**
     * 首页获取课程列表
     * @param name 课程名称
     * @param instructorName 讲师姓名
     * @param companyName 公司名称
     * @param startDate 最早开课日期
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 课程列表
     */
    @GetMapping("/courses/{name}/{instructor_name}/{company_name}/{start_date}/{page_size}/{current_page}")
    public ResultInfo getCourses(@PathVariable(value = "name", required = false) String name,
                                 @PathVariable(value = "instructor_name", required = false) String instructorName,
                                 @PathVariable(value = "company_name", required = false) String companyName,
                                 @PathVariable(value = "start_date", required = false) String startDate,
                                 @PathVariable(value = "page_size", required = false) Integer pageSize,
                                 @PathVariable(value = "current_page", required = false) Integer currentPage) {
        CourseList courseList = new CourseList();
        courseList.setName(name.equals("null") ? null : name);
        courseList.setInstructorName(instructorName.equals("null") ? null : instructorName);
        courseList.setCompanyName(companyName.equals("null") ? null : companyName);
        courseList.setStartDate(startDate);

        ResultInfo info = new ResultInfo();
        try {
            info.setFlag(true);
            info.setData(homeService.getCourses(courseList, pageSize, currentPage));
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("获取课程列表失败");
        }
        return info;
    }

}
