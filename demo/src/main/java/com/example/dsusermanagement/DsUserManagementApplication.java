package com.example.dsusermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class DsUserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DsUserManagementApplication.class, args);

    }

    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             registry.addMapping("/**")
    //                     .allowedMethods("*")
    //                     .allowedOrigins("*")
    //                     .allowedHeaders("*");
    //         }
    //     };
    // }
}
