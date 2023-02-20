package com.cos.reflect.filter;

import com.cos.reflect.controller.IndexController;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

@WebFilter(filterName = "Dispatcher", urlPatterns = "/*")
public class Dispatcher implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        System.out.println("컨텍스트 패스: " + req.getContextPath()); // 프로젝트 시작 주소
        System.out.println("식별자 주소: " + req.getRequestURI()); // 끝 주소
        System.out.println("전체 주소 : " + req.getRequestURL()); // 전체 주소

        // /user 파싱하기
        String endPoint = req.getRequestURI().replaceAll(req.getContextPath(), "");
        System.out.println("엔드포인트 : " + endPoint);

        IndexController userController = new IndexController();
        if(endPoint.equals("/index")) {
            userController.index();
        }
    }
}
