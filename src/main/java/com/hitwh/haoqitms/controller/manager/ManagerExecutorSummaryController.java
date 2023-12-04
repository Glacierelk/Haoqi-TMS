package com.hitwh.haoqitms.controller.manager;

import com.hitwh.haoqitms.entity.ResultInfo;
import com.hitwh.haoqitms.service.manager.ManagerExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager/executor")
public class ManagerExecutorSummaryController {
    private final ManagerExecutorService managerExecutorService;

    @Autowired
    public ManagerExecutorSummaryController(ManagerExecutorService managerExecutorService) {
        this.managerExecutorService = managerExecutorService;
    }

    /**
     * 获取执行人数量 [manager]
     * @return 执行人数量
     */
    @GetMapping("/count")
    public ResultInfo getExecutorCount() {
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setData(managerExecutorService.getExecutorCount());
            resultInfo.setFlag(true);
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取执行人数量失败");
        }
        return resultInfo;
    }

    /**
     * 获取执行人概要信息 [manager]
     * @param pageSize 页面大小
     * @param currentPage 当前页
     * @return 执行人概要信息
     */
    @GetMapping("/list/{page_size}/{current_page}")
    public ResultInfo getExecutorSummary(@PathVariable("page_size") Integer pageSize,
                                         @PathVariable("current_page") Integer currentPage) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setData(managerExecutorService.getExecutorSummary(pageSize, currentPage));
            resultInfo.setFlag(true);
        } catch (Exception e) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("获取执行人概要信息失败");
        }
        return resultInfo;
    }
}
