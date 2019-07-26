package com.bo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {

    /**
     * 想做到的程度，在一个list集合中存放随机数，如果集合中有对应随机数便不再循环，否则到100次结束循环
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    public List<Double> hello(){
        List<Double> list = new ArrayList<>();
        List<Double> list1 = new ArrayList<>();
        Double random = null;
        int num = 0;
        for(;!list1.contains(random);){
            if(random != null){
                list1.add(random);
            }
            random = Math.random();
            list.add(random);
            num++;
            if(num>100){
                break;
            }
        }
        System.out.println(num);
        return list;
    }

    @RequestMapping("/hello2")
    public String test02(){
        String s = new String();
        System.out.println(s);
        return "index";
    }

}
