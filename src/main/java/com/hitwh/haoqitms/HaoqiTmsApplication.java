package com.hitwh.haoqitms;

import com.hitwh.haoqitms.filter.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HaoqiTmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaoqiTmsApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<MyFilter> corsFilter() {
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
