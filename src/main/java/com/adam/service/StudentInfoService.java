package com.adam.service;

import com.adam.domain.StudentInfo;
import com.adam.mapper.StudentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author adam
 * @auther adam
 * @date 2022/11/7
 * @apiNote com.adam.service
 */
@Service
public class StudentInfoService {
    @Autowired
    StudentInfoMapper studentInfoMapper;

    public void insertStudentInfo(List<StudentInfo> list) {
        studentInfoMapper.insertStudentInfo(list);
    }

    public void updateStudentInfo(StudentInfo studentInfo) {
        studentInfoMapper.updateStudentInfo(studentInfo);
    }
    public void deleteStudentInfo(String name) {
        studentInfoMapper.deleteStudentInfo(name);
    }
    public List<StudentInfo> selectStudentInfo(String name) {
        return studentInfoMapper.selectStudentInfo(name);
    }
    public List<StudentInfo> selectAllStudentInfo() {
        return studentInfoMapper.selectAllStudentInfo();
    }
}
