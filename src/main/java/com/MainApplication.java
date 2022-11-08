package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author adam
 * @auther adam
 * @date 2022/11/7
 * @apiNote PACKAGE_NAME
 */
@SpringBootApplication
@MapperScan("com.adam.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
