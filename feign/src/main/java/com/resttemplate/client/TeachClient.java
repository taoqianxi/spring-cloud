package com.resttemplate.client;

import com.resttemplate.client.error.FeighError;
import com.resttemplate.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * fallback ,配置服务器熔断类
 */
@FeignClient(value = "teacher",fallback = FeighError.class)
public interface TeachClient {

    @GetMapping("/student/all")
    List<Student> studentList();
}
