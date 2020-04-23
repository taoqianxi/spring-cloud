package com.resttemplate.client.error;

import com.resttemplate.client.TeachClient;
import com.resttemplate.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeighError implements TeachClient {

    @Override
    public List<Student> studentList() {
        return null;
    }
}
