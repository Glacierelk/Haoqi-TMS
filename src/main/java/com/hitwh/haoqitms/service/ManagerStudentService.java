package com.hitwh.haoqitms.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface ManagerStudentService {
    @GetMapping("/total")
    public Integer countTotalStudent();

}
