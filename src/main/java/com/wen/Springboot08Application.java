package com.wen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wen.pojo")
public class Springboot08Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08Application.class, args);
    }

}
