package com.teacher.service;

import com.teacher.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private static List<Student> list= new ArrayList<>();
    static {
        list.add(new Student("小明",16,"小明成绩还是不错的！"));
        list.add(new Student("李子",19,"李子就喜欢玩游戏！"));
        list.add(new Student("小白",16,"小白跟小明玩的不错"));
        list.add(new Student("小红",16,"小红偷偷的喜欢小明"));

    }
    public List<Student> studentList(){
        return list;
    }
}
