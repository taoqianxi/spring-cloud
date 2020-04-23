package com.resttemplate.controller;

import com.resttemplate.client.TeachClient;
import com.resttemplate.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("consumers")
    public List<Student> studentList(){
        return restTemplate.getForEntity("http://teacher:8001/student/all",List.class).getBody();
    }

    @Autowired
    private TeachClient teachClient;

    @GetMapping("feign")
    public List<Student> feign(){
        return teachClient.studentList();
    }
}
