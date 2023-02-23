package com.cos.reflect.filter;

import com.cos.reflect.anno.RequestMappping;
import com.cos.reflect.controller.UserController;
import com.cos.reflect.controller.dto.LoginDto;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

public class Dispatcher implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

//        System.out.println("식별자 주소: " + req.getRequestURI()); // 끝 주소
//        System.out.println("전체 주소 : " + req.getRequestURL()); // 전체 주소

        // 리플렉션 -> 메서드를 런타임 시점에서 찾아서 실행 (컴파일 시점 x)
        UserController userController = new UserController();
        Method[] methods = userController.getClass().getDeclaredMethods(); // 그 파일의 메서드만 가져옴

//        for(Method method : methods) {
//            System.out.println(method.getName());
//            if(req.getRequestURI().equals("/" + method.getName())) {
//                try {
//                    method.invoke(indexController);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        for(Method method : methods) {
            Annotation annotation = method.getDeclaredAnnotation(RequestMappping.class);
            RequestMappping requestMappping = (RequestMappping) annotation; // requestMapping 내에서 선언된 함수를 사용하기 위해 다운캐스팅
            System.out.println(requestMappping.value());

            if(requestMappping.value().equals(req.getRequestURI())) {
                try {
                    Parameter[] parameters = method.getParameters();
                    String path = null;
                    if(parameters.length != 0) {
//                        System.out.println("parameters[0].getType() : " + parameters[0].getType());
                        Object dtoInstance = parameters[0].getType().newInstance();
//                        String username = req.getParameter("username");
//                        String password = req.getParameter("password");
//                        System.out.println("username : " + username);
//                        System.out.println("password : " + password);
                        Enumeration<String> keys = req.getParameterNames(); // username, password
                        // key 값을 변형, username -> setUsername, password -> setPassword
                        path = "index";
                    } else {
                        path = (String) method.invoke(userController, LoginDto.class);
                    }

                    RequestDispatcher dis = req.getRequestDispatcher(path); // 필터를 다시 안탐
                    dis.forward(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
