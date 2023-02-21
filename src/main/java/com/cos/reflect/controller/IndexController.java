package com.cos.reflect.controller;

import com.cos.reflect.anno.RequestMappping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/index")
    @RequestMappping("/index")
    public String index() {
        return "index";
    }

    @RequestMappping("/join")
    public String join() {
        System.out.println("join() 실행");
        return "index";
    }

    @RequestMappping("/login")
    public String login() {
        System.out.println("login() 실행");
        return "index";
    }

    @RequestMappping("/user")
    public String user() {
        System.out.println("user() 실행");
        return "index";
    }

    @RequestMappping("/hello")
    public String hello() {
        System.out.println("hello() 실행");
        return "index";
    }
}
