package com.adam.controller;

import com.adam.domain.Respone;
import com.adam.domain.StudentInfo;
import com.adam.service.StudentInfoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        OutputStream stream = response.getOutputStream();
//        PrintWriter writer = response.getWriter();
        if (check.length() > 0) {
            System.out.println(check + time + "\n");
            dealParamsError(stream, response);
            return;
        }

        StudentInfo s = new StudentInfo(name, age, height, time);

        try {
            List<StudentInfo> list = new ArrayList<>();
            list.add(s);
            studentInfoService.insertStudentInfo(list);

            dealSuccess("addUser - "+ name +" successful." + "\n", name, stream, response);

        } catch (Exception e) {
            System.out.println("页面无法访问" + e);
            dealError(stream, response);
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
        OutputStream stream = response.getOutputStream();
//        PrintWriter writer = response.getWriter();
        if (check.length() > 0) {
            System.out.println(check + time + "\n");
            dealParamsError(stream, response);
            return;
        }
        try {
            studentInfoService.deleteStudentInfo(name);

            dealSuccess("deleteUser - "+ name +" successful." + "\n", name, stream, response);

        } catch (Exception e) {
            System.out.println("页面无法访问" + "User " + name + "not exit.\n" + e);
            dealError(stream, response);
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
        OutputStream stream = response.getOutputStream();
//        PrintWriter writer = response.getWriter();
        if (check.length() > 0) {
            System.out.println(check + time + "\n");
            dealParamsError(stream, response);
            return;
        }
        StudentInfo studentInfo = new StudentInfo(name, age, height, time);

        try {
            studentInfoService.updateStudentInfo(studentInfo);

            dealSuccess("updateUser - "+ name +" successful." + "\n", name, stream, response);

        } catch (Exception e) {
            System.out.println("页面无法访问" + "User " + name + "not exit.\n" + e);
            dealError(stream, response);
            e.printStackTrace();
        } finally {
            System.out.println("updateUser finally");
        }
    }
    @RequestMapping(value = "test/getUsers", method = RequestMethod.POST)
    public void getUsers(String name, HttpServletResponse response) throws IOException {
        System.out.println("---getUser---" + name);
        OutputStream stream = response.getOutputStream();
//        PrintWriter writer = response.getWriter();
        if (name == null || name.length() <= 0) {
            System.out.println("getUser - " +" error." + "\n" + name + "\n");
            dealParamsError(stream, response);
            return;
        }
        try {
            List<StudentInfo> studentInfos = studentInfoService.selectStudentInfo(name);
            String message = "getUser - " +" successful." + "\n";
            response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
            Gson gson = new Gson();
            Respone<List<StudentInfo>> listRespone = new Respone<>("0", message, studentInfos);
            String data = gson.toJson(listRespone);


            stream.write(data.getBytes("UTF-8"));
            //使用Writer向客户端写入中文:
            response.setCharacterEncoding("UTF-8");//设置Response的编码方式为UTF-8

            response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用
            //response.setContentType("text/html;charset=UTF-8");同上句代码作用一样
//            writer.write(data);
        } catch (Exception e) {
            System.out.println("页面无法访问" + e);
            dealError(stream, response);
            e.printStackTrace();
        } finally {
            System.out.println("getUser finally");
        }
    }

    @RequestMapping(value = "test/getAllUser", method = RequestMethod.POST)
    public void getAllUser(HttpServletResponse response) throws IOException {
        System.out.println("---getAllUser---");
        OutputStream stream = response.getOutputStream();
//        PrintWriter writer = response.getWriter();
        try {
            List<StudentInfo> studentInfos = studentInfoService.selectAllStudentInfo();
            String message = "getAllUser - " +" successful." + "\n";
            response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
            Gson gson = new Gson();
            Respone<List<StudentInfo>> listRespone = new Respone<>("0", message, studentInfos);
            String data = gson.toJson(listRespone);


            stream.write(data.getBytes("UTF-8"));
            //使用Writer向客户端写入中文:
            response.setCharacterEncoding("UTF-8");//设置Response的编码方式为UTF-8

            response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用
            //response.setContentType("text/html;charset=UTF-8");同上句代码作用一样
//            writer.write(data);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            System.out.println("页面无法访问" + e);
            dealError(stream, response);
            e.printStackTrace();
        } finally {
            System.out.println("getAllUser finally");
        }
    }
    private void dealSuccess(String message, String dataString, OutputStream stream, HttpServletResponse response) throws IOException {

        Respone<String> listRespone = new Respone<>("0", message, dataString);
        Gson gson = new Gson();
        String data = gson.toJson(listRespone);


        stream.write(data.getBytes("UTF-8"));
        //使用Writer向客户端写入中文:
        response.setCharacterEncoding("UTF-8");//设置Response的编码方式为UTF-8

        response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

//        writer.write(data);
        stream.flush();
        stream.close();
    }
    private void dealParamsError(OutputStream stream,HttpServletResponse response) throws IOException {
        String message = "参数异常，请稍后再试";
        Respone<String> listRespone = new Respone<>("1", message, message);
        Gson gson = new Gson();
        String data = gson.toJson(listRespone);


        stream.write(data.getBytes("UTF-8"));
        //使用Writer向客户端写入中文:
        response.setCharacterEncoding("UTF-8");//设置Response的编码方式为UTF-8

        response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

//        writer.write(data);
        stream.flush();
        stream.close();
    }

    private void dealError(OutputStream stream, HttpServletResponse response) throws IOException {
        String message = "网络异常，请稍后再试";
        Respone<String> listRespone = new Respone<>("1", message, message);
        Gson gson = new Gson();
        String data = gson.toJson(listRespone);


        stream.write(data.getBytes("UTF-8"));
        //使用Writer向客户端写入中文:
        response.setCharacterEncoding("UTF-8");//设置Response的编码方式为UTF-8

        response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8,其实设置了本句，也默认设置了Response的编码方式为UTF-8，但是开发中最好两句结合起来使用

//        writer.write(data);
        stream.flush();
        stream.close();
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
