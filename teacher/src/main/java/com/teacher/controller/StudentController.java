package com.teacher.controller;

import com.teacher.entity.Student;
import com.teacher.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Value("${server.port}")
    private String port;

    @Value("${server.portConfig}")
    private String massage;

    @GetMapping("all")
    public List<Student> studentList(){
        return studentService.studentList();
    }

    @GetMapping("port")
    public String port(){
        return port;
    }

    @GetMapping("massage")
    public String massage(){
        return massage;
    }
}
