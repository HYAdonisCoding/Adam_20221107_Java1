package com.adam.mapper;

import com.adam.domain.StudentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther adam
 * @date 2022/11/7
 * @apiNote com.adam.mapper
 */

@Repository
public interface StudentInfoMapper {
    void insertStudentInfo(List<StudentInfo> list);

    void updateStudentInfo(StudentInfo studentInfo);
    void deleteStudentInfo(String name);
    List<StudentInfo> selectStudentInfo(String name);
    List<StudentInfo> selectAllStudentInfo();
}