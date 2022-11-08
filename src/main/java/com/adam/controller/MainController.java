package com.adam.controller;

import com.adam.domain.StudentInfo;
import com.adam.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author adam
 * @auther adam
 * @date 2022/11/7
 * @apiNote controller
 */
@RestController
public class MainController {

    @Autowired
    private StudentInfoService studentInfoService;

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
    public void addUser(String name, String age, String height, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        System.out.println("---addUser---" + " - " + name + " - " + age + " - " + height);
        String check = check(name, age, height);
        String time = getCurrentTime();
        if (check.length() > 0) {
            response.getWriter().print(check + time + "\n");
            return;
        }

        StudentInfo s = new StudentInfo(name, age, height, time);

        try {
            List<StudentInfo> list = new ArrayList<>();
            list.add(s);
            studentInfoService.insertStudentInfo(list);
            response.getWriter().print("addUser - "+ name +" successful." + "\n");

        } catch (Exception e) {
            System.out.println("页面无法访问" + e);
            e.printStackTrace();
        } finally {
            System.out.println("addUser finally");
        }
    }
    @RequestMapping(value = "test/deleteUser", method = RequestMethod.POST)
    public void deleteUser(String name, String age, String height, HttpServletResponse response) throws IOException {
        System.out.println("---deleteUser---" + " - " + name + " - " + age + " - " + height);
        String check = check(name, age, height);
        String time = getCurrentTime();
        if (check.length() > 0) {
            response.getWriter().print(check + time + "\n");
            return;
        }
        try {
            studentInfoService.deleteStudentInfo(name);
            response.getWriter().print("deleteUser - "+ name +" successful." + "\n");

        } catch (Exception e) {
            System.out.println("页面无法访问" + "User " + name + "not exit.\n" + e);

            e.printStackTrace();
        } finally {
            System.out.println("addUser finally");
        }
    }
    @RequestMapping(value = "test/updateUser", method = RequestMethod.POST)
    public void updateUser(String name, String age, String height, HttpServletResponse response) throws IOException {
        System.out.println("---updateUser---" + " - " + name + " - " + age + " - " + height);
        String check = check(name, age, height);
        String time = getCurrentTime();
        if (check.length() > 0) {
            response.getWriter().print(check + time + "\n");
            return;
        }
        StudentInfo studentInfo = new StudentInfo(name, age, height, time);

        try {
            studentInfoService.updateStudentInfo(studentInfo);
            response.getWriter().print("updateUser - "+ name +" successful." + "\n");

        } catch (Exception e) {
            System.out.println("页面无法访问" + "User " + name + "not exit.\n" + e);

            e.printStackTrace();
        } finally {
            System.out.println("updateUser finally");
        }
    }
    @RequestMapping(value = "test/getUsers", method = RequestMethod.POST)
    public void getUsers(String name, HttpServletResponse response) throws IOException {
        System.out.println("---getUser---" + name);
        if (name == null || name.length() <= 0) {
            response.getWriter().print("getUser - " +" error." + "\n" + name + "\n");
            return;
        }
        try {
            List<StudentInfo> studentInfos = studentInfoService.selectStudentInfo(name);
            response.getWriter().print("getUser - " +" successful." + "\n" + studentInfos + "\n");

        } catch (Exception e) {
            System.out.println("页面无法访问" + e);

            e.printStackTrace();
        } finally {
            System.out.println("getUser finally");
        }
    }

    @RequestMapping(value = "test/getAllUser", method = RequestMethod.POST)
    public void getAllUser(HttpServletResponse response) throws IOException {
        System.out.println("---getAllUser---");
        try {
            List<StudentInfo> studentInfos = studentInfoService.selectAllStudentInfo();
            response.getWriter().print("getAllUser - " +" successful." + "\n" + studentInfos + "\n");

        } catch (Exception e) {
            System.out.println("页面无法访问" + e);

            e.printStackTrace();
        } finally {
            System.out.println("getAllUser finally");
        }
    }


    private String check(String name, String age, String height) {
        if (name == null || age == null || height == null) {
            return "Parameters is not null!";
        }
        if (name.length() <= 0) {
            return "Parameters name is error!";
        } else if (Double.parseDouble(age) < 18) {
            return "Parameters user is error!";
        } else if (Double.parseDouble(height) < 1) {
            return "Parameters user is error!";
        }
        return "";
    }
    public static String getCurrentTime() {
        Calendar now = Calendar.getInstance();

        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String dateNowStr = sdf.format(d);
        return dateNowStr;

    }
}
