package com.hitwh.haoqitms.service.manager;

import com.hitwh.haoqitms.entity.Pagination;

public interface ManagerExecutorService {
    /**
     * 获取当前执行人数量
     * @return 执行人数量
     */
    Integer getExecutorCount();

    /**
     * 获取当前执行人汇总信息
     * @return 执行人数量
     */
    Pagination getExecutorSummary(Integer pageSize, Integer currentPage);
}
