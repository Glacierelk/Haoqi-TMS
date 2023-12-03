package com.hitwh.haoqitms.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface ManagerInstructorService {
    @GetMapping("/total")
    public Integer countTotalInstructor();

}
