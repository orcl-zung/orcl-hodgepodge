package com.orcl.test.jpa;

import com.orcl.reflection.anno.Table;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-20 22:12
 * @history: 2022-07-20 22:12 created by orcl
 */
@Table("t_jpa_user")
public class User {

    private String name;

    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
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
