package com.softvision.example.springboot.config;


import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;


// http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    String swaggerDocPrefix = ""; // blank for now
    @Bean
    public Docket groupsApi(ServletContext servletContext) {
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


    // These two methods allow moving the swagger context for swagger-ui.html to <swaggerDocPrefix>/swagger-ui.html
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController(swaggerDocPrefix + "/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
        registry.addRedirectViewController(swaggerDocPrefix + "/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController(swaggerDocPrefix + "/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController(swaggerDocPrefix + "/swagger-resources", "/swagger-resources");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(swaggerDocPrefix + "/**").addResourceLocations("classpath:/META-INF/resources/");
    }

}

