package com.mycom.enjoy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//view, Interceptor 설정 등등 여러 설정을 할 수 있다.
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	//임시 Interceptor
	
//	@Autowired
//	LoginInterceptor loginInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(loginInterceptor)
//				.addPathPatterns("/**")
//				.excludePathPatterns(
//						"/",//root
//						"/login/**",//login
//						"/users/**",//register
//						"/codes/**",//common Code
//						"/css/**", //css
//						"/js/**", //js
//						"/img/**", //img
//						"/favicon.ico" //icon
//				);
//	}
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                //get, post는 신뢰하는 기능이라고 생각하고 처리.
                //하지만 나머지 put, delete는 신뢰할 수 없는 메소드로 지정되어있어서
                //브라우저에 options로 물어본다. put과 delete 사용해도되는지?
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)//중요
                .maxAge(3000);
    }
}
