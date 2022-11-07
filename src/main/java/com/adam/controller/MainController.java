package com.adam.controller;

import com.adam.domain.StudentInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adam
 * @auther adam
 * @date 2022/11/7
 * @apiNote controller
 */
@RestController
public class MainController {
    private List<StudentInfo> result = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    @GetMapping("test")
    public String test() {
        return "Hello springboot!";
    }

    @RequestMapping(value = "test/ready", method = RequestMethod.GET)
    public String testReady() {
        return "Everything is ready.";
    }

    @GetMapping(value = "test/getData")
    public String getLinkData(String adcode, String user) {
        System.out.println("------------------- adcode: " + adcode +", user: " + user + " -------------------");
        if (adcode.length() != 6) {
            return "Parameters adcode is error!";
        } else if (!"adam".equals(user)) {
            return "Parameters user is error!";
        }
        return "Data is downloading now, please wait";
    }

    @RequestMapping(value = "test/addUser", method = RequestMethod.POST)
    public String addUser(String name, String age, String height) {
        System.out.println("---addData---" + " - " + name + " - " + age + " - " + height);
        String check = check(name, age, height);
        if (check.length() > 0) {
            return check;
        }
        StudentInfo s = new StudentInfo(name, age, height, result.get(result.size()-1).getId()+1);
        result.add(s);
        return "addUser successful.";
    }
    @RequestMapping(value = "test/deleteUser", method = RequestMethod.POST)
    public String deleteUser(String name, String age, String height) {
        System.out.println("---deleteUser---" + " - " + name + " - " + age + " - " + height);
        String check = check(name, age, height);
        if (check.length() > 0) {
            return check;
        }
        for (int i = 0; i < result.size(); i++) {
            StudentInfo s = result.get(i);
            if (s.getName().equals(name)) {
                result.remove(s);
                return "delete successful.";
            }
        }
        return "User " + name + "not exit.";
    }
    @RequestMapping(value = "test/updateUser", method = RequestMethod.POST)
    public String updateUser(String name, String age, String height) {
        System.out.println("---updateUser---" + " - " + name + " - " + age + " - " + height);
        String check = check(name, age, height);
        if (check.length() > 0) {
            return check;
        }
        for (int i = 0; i < result.size(); i++) {
            StudentInfo s = result.get(i);
            if (s.getName().equals(name)) {
                s.setAge(age);
                s.setHeight(height);
                return "update successful.";
            }
        }
        return "User " + name + "not exit.";
    }
    @RequestMapping(value = "test/getAllUser", method = RequestMethod.POST)
    public List<StudentInfo> getAllUser() {
        System.out.println("---getAllUser---");
        return result;
    }

    private String check(String name, String age, String height) {
        if (name.length() <= 0) {
            return "Parameters name is error!";
        } else if (Double.parseDouble(age) < 18) {
            return "Parameters user is error!";
        } else if (Double.parseDouble(height) < 1) {
            return "Parameters user is error!";
        }
        return "";
    }
}
