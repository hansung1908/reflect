package com.cos.reflect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        System.out.println("index() 실행");
        return "index";
    }
}
