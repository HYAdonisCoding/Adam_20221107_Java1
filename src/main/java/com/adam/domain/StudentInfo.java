package com.adam.domain;

/**
 * @auther adam
 * @date 2022/11/7
 * @apiNote com.adam.domain
 */
public class StudentInfo {
    private String name;
    private String age;
    private String height;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentInfo(String name, String age, String height, int id) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.id = id;
    }
}
