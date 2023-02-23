package com.cos.reflect.controller;

import com.cos.reflect.anno.RequestMappping;
import com.cos.reflect.controller.dto.JoinDto;
import com.cos.reflect.controller.dto.LoginDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/index")
    @RequestMappping("/index")
    public String index() {
        return "index";
    }

    @RequestMappping("/join")
    public String join(JoinDto dto) {
        System.out.println("join() 실행");
        return "index";
    }

    @RequestMappping("/login")
    public String login(LoginDto dto) {
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
