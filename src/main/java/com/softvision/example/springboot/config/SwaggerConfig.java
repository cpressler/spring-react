package com.softvision.example.springboot.config;


import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

// http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket groupsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.softvision.example.springboot.web"))
                .paths(PathSelectors.any())
                //.paths(regex("/.*"))
                .build()
                .apiInfo(apiInfo())
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "JUG Users Group Manage",
                "Example of using Spring Boot and React",
                "1", "",
                new Contact("Henderson Studio", "", "chester.pressler@softvision.com"),
                "", "",
                Collections.emptyList()
        );
    }

}

