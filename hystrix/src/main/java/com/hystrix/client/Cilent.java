package com.hystrix.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("teacher")
public interface Cilent {

    @GetMapping("student/port")
    String prot();
}
