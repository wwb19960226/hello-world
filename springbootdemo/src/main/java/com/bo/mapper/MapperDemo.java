package com.bo.mapper;

import com.bo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapperDemo {
    List<Student> queryStudents();

}
