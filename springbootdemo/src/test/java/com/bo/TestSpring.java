package com.bo;


import com.bo.mapper.MapperDemo;
import com.bo.pojo.Student;
import com.bo.pojo.Teacher;
import com.bo.service.RedisService;
import com.bo.utils.DataBaseUtils;
import com.bo.utils.ZsetRedisBaseUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpring {

    /*private final Logger log = LoggerFactory.getLogger(TestSpring.class);*/

    @Autowired
    private Student student;

    @Autowired
    private DataBaseUtils utils;

    @Autowired
    private MapperDemo mapperDemo;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ZsetRedisBaseUtils zsetRedisBaseUtils;

   @Test
    public void test01() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher = new Teacher();
        teacher.setName("嘉德");
        teacher.setAge(22);
        teacher.setSubject("计算机");
        Teacher teacher1 = new Teacher();
        teacher1.setName("月色");
        teacher1.setAge(21);
        teacher1.setSubject("IT");
        teachers.add(teacher);
        teachers.add(teacher1);
       /* log.warn(teacher.getName());*/
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

    @Test
    public void test03() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher = new Teacher();
        teacher.setId(new Long(19));
        teacher.setName("嘉德亲王");
        teacher.setAge(22);
        teacher.setSubject("计算机");
        Teacher teacher1 = new Teacher();
        teacher1.setId(new Long(20));
        teacher1.setName("月色修魔");
        teacher1.setAge(21);
        teacher1.setSubject("IT");
        teachers.add(teacher);
        teachers.add(teacher1);
        /* log.warn(teacher.getName());*/
        utils.batchUpdate(teachers,Teacher.class);
    }

    /**
     * 测试redis是否可用
     */
    @Test
    public void test04(){
        boolean set = redisService.set("test", "make");
        System.out.println(set);
    }

    /**
     * 测试Zset工具类是否可用
     */
    @Test
    public void test05(){
        redisService.zAdd("key1","zhangsan",1);
        redisService.zAdd("key1","lisi",2);
        redisService.zAdd("key1","wangwu",3);
        redisService.zAdd("key1","zhaoliu",3);
        Object key1 = zsetRedisBaseUtils.ZsetScoreMax("key1");

        System.out.println(key1.toString());
    }

}
