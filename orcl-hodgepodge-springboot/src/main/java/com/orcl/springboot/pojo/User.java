package com.orcl.springboot.pojo;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-06 11:28
 * @history: 2022-08-06 11:28 created by Administrator
 */
public class User {

    private String name;

    private Integer age;

    private Pet pet;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
