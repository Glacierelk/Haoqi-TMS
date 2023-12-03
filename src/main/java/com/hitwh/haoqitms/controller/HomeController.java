package com.hitwh.haoqitms.controller;


import com.hitwh.haoqitms.entity.CourseApplication;
import com.hitwh.haoqitms.entity.CourseList;
import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.CourseApplicationService;
import com.hitwh.haoqitms.service.HomeService;
import com.hitwh.haoqitms.service.TrainingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/home")
@RestController
public class HomeController {
    private final HomeService homeService;
    private final TrainingApplicationService trainingApplicationService;
    private final CourseApplicationService courseApplicationService;

    @Autowired
    public HomeController(HomeService homeService, TrainingApplicationService trainingApplicationService, CourseApplicationService courseApplicationService) {
        this.homeService = homeService;
        this.trainingApplicationService = trainingApplicationService;
        this.courseApplicationService = courseApplicationService;
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

    /**
     * 获取所有公司名称
     * @return 公司名称列表
     */
    @GetMapping("/companyNames")
    public ResultInfo getCompanyNames() {
        ResultInfo info = new ResultInfo();
        List<String> companyNames = trainingApplicationService.selectAllCompanyName();
        try {
            info.setFlag(true);
            info.setData(companyNames);
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("获取公司名称列表失败");
        }
        return info;
    }

    /**
     * 检查团报码是否有效
     * @param promoCode 团报码
     */
    @GetMapping("/checkPromoCode/{promoCode}")
    public ResultInfo checkPromoCode(@PathVariable(value = "promoCode") String promoCode) {
        ResultInfo info = new ResultInfo();
        String companyName = trainingApplicationService.selectCompanyNameByPromoCode(promoCode);
        try {
            info.setFlag(true);
            info.setData(companyName);
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("检查团报码失败");
        }
        return info;
    }

    /**
     * 新建课程申请
     * @param courseApplication 课程id
     */
    @PostMapping("/createCourseApplication")
    public ResultInfo createCourseApplication(@RequestBody CourseApplication courseApplication) {
        ResultInfo info = new ResultInfo();
        String promoCode = trainingApplicationService.selectPromoCodeByCourseId(courseApplication.getCourseId());
        if ("" == courseApplication.getCompanyName() || ("" != courseApplication.getCompanyName() && ""  == courseApplication.getPromoCode())) {
            // 公司名为null，团报码肯定为null,直接新建课程申请
            // 公司名不为null，团报码为null，也新建课程申请
            if("" == courseApplication.getCompanyName()){
                courseApplication.setCompanyName(null);
            }else {
                courseApplication.setPromoCode(null);
            }
            try {
                info.setFlag(true);
                info.setData(courseApplicationService.createCourseApplication(courseApplication));
            } catch (Exception e) {
                info.setFlag(false);
                info.setErrorMsg("新建课程申请失败");
                e.printStackTrace();
            }
        }else{
            // 公司名不为null，团报码不为null，检查团报码是否有效
            if (promoCode.equals(courseApplication.getPromoCode())) {
                // 团报码有效，新建课程申请
                try {
                    info.setFlag(true);
                    info.setData(courseApplicationService.createCourseApplication(courseApplication));
                } catch (Exception e) {
                    info.setFlag(false);
                    info.setErrorMsg("新建课程申请失败");
                    e.printStackTrace();
                }
            }else{
                // 团报码无效，返回错误信息
                info.setFlag(false);
                info.setErrorMsg("团报码无效，请检查团报码是否正确");
            }
        }
        return info;
    }
}
