package com.jh.guestbook;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration // bean 객체 생성
//@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // allowedOrigins 메소드를 이용해서 자원 공유를 허락할 Origin을 지정
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(3000);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("/guestbook", "classpath:/static/")
//                .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
//    }

}
