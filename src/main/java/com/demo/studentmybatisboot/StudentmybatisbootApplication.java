package com.demo.studentmybatisboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@ComponentScan(basePackages = {"com.action","com.biz"})
@MapperScan(basePackages = {"com.mapper"})
public class StudentmybatisbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentmybatisbootApplication.class, args);
    }

}
