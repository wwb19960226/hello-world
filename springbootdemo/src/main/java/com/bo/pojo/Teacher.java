package com.bo.pojo;

import com.bo.annotate.DbColumn;
import com.bo.annotate.DbTable;

@DbTable(name = "t_teacher")
public class Teacher {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    @DbColumn(name = "f_name",canModify = true)
    private String name;

    /**
     * 年龄
     */
    @DbColumn(name = "f_age",canModify = true)
    private Integer age;

    /**
     * 性别
     */
    @DbColumn(name = "f_subject",canModify = false)
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
