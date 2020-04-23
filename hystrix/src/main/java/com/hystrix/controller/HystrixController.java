package com.hystrix.controller;

import com.hystrix.client.Cilent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {

    @Autowired
    Cilent cilent;

    @GetMapping("port")
    public String port(){
        return cilent.prot();
    }
}
