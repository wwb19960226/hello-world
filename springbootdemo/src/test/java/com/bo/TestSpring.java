package com.bo;


import com.bo.mapper.MapperDemo;
import com.bo.pojo.Student;
import com.bo.pojo.Teacher;
import com.bo.utils.DataBaseUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpring {

    @Autowired
    private Student student;

    @Autowired
    private DataBaseUtils utils;

    @Autowired
    private MapperDemo mapperDemo;


    @Test
    public void test01(){
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher = new Teacher();
        teacher.setName("嘉德");
        teacher.setAge(22);
        teacher.setSubject("计算机");
        teachers.add(teacher);
        utils.batchInstert(teachers);
    }

    /**
     *测试mybatis配置是否可用
     */
    @Test
    public void queryStudentsTest(){
        List<Student> students = mapperDemo.queryStudents();
        for(Student student:students){
            System.out.println("===========================");
            System.out.println(student.toString());
        }
    }

}
