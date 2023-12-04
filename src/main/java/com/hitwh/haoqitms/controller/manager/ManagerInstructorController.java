package com.hitwh.haoqitms.controller.manager;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.manager.ManagerInstructorService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@NoArgsConstructor
@RequestMapping("/manager/instructor")
@RestController
public class ManagerInstructorController {

    private ManagerInstructorService managerInstructorService;


    /**
     * 查看讲师信息
     *
     * @return 讲师数量
     */
    @GetMapping("/total")
    public ResultInfo countTotalInstructor() {
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(managerInstructorService.countTotalInstructor());
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("查询讲师信息失败");
        }
        return resultInfo;
    }

}
