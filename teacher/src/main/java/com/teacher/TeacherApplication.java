package com.teacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class TeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherApplication.class,args);
    }
}
