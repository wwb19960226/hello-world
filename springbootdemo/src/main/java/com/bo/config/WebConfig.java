package com.bo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell-3020 on 2017/8/15.
 */
@Configuration
public class WebConfig {


    @Bean
    FilterRegistrationBean tokenFilter() {
        FilterRegistrationBean filterReg = new FilterRegistrationBean(new TokenFilter());
        //优先级
        filterReg.setOrder(70);
        filterReg.setDispatcherTypes(DispatcherType.REQUEST);
        //匹配路径
        List<String> urlPatterns = new ArrayList<>();
//        urlPatterns.add("/*");
        filterReg.addUrlPatterns("/*");
        filterReg.addInitParameter("exclusions","*.js,*.css");
//        filterReg.setUrlPatterns(urlPatterns);
        System.out.println("====来了");
        return filterReg;
    }
}