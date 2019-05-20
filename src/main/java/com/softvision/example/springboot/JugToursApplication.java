package com.softvision.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class JugToursApplication {

    public static void main(String[] args) {

        SpringApplication.run(JugToursApplication.class, args);
    }
}