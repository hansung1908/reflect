package com.cos.reflect;

import com.cos.reflect.filter.Dispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReflectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReflectApplication.class, args);
	}

	// @WebFilter + ServletComponentScan으로 필터를 설정할 시 @SpringBootApplication에 포함된 @Component로 인해 필터가 두번 실행
	@Bean
	public FilterRegistrationBean setFilterRegistration() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new Dispatcher());
		// filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*")); // list 를 받는 메소드
		filterRegistrationBean.addUrlPatterns("/*"); // string 여러개를 가변인자로 받는 메소드
		return filterRegistrationBean;
	}
}
