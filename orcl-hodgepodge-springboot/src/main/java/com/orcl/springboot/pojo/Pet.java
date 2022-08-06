package com.orcl.springboot.pojo;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-06 11:28
 * @history: 2022-08-06 11:28 created by Administrator
 */
public class Pet {

    private String name;

    private Integer age;

    public Pet(String name) {
        this.name = name;
    }

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
}
