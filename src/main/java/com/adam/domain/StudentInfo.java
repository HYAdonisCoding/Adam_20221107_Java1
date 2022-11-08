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
    private String updatetime;

    public StudentInfo(String name, String age, String height, int id, String updatetime) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.id = id;
        this.updatetime = updatetime;
    }

    public StudentInfo(String name, String age, String height, String updatetime) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.updatetime = updatetime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

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

    @Override
    public String toString() {
        return "StudentInfo{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", id=" + id +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
