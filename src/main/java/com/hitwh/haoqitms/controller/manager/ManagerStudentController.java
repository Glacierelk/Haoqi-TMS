package com.hitwh.haoqitms.controller.manager;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.manager.ManagerStudentService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RequestMapping("/manager/student")
@RestController
public class ManagerStudentController {

    private ManagerStudentService managerStudentService;


    /**
     * 查看培训学员信息
     *
     * @return 所有学员数量
     */
    @GetMapping("/total")
    public ResultInfo countTotalStudent() {
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setFlag(true);
            resultInfo.setData(managerStudentService.countTotalStudent());
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("查询失败");
        }
        return resultInfo;
    }

}
