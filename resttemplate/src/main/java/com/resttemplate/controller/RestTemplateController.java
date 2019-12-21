package com.resttemplate.controller;

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
        return restTemplate.getForEntity("http://desktop-a79quek:8001/student/all",List.class).getBody();
    }
}
