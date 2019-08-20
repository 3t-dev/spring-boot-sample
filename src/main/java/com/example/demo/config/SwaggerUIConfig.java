package com.example.demo.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.time.LocalDateTime;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerUIConfig {

    @Autowired
    ServletContext servletContext;

    @Autowired
    TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()).host("host:port").pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return "";
                    }
                })
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Demo service",
                "This is a demo",
                "v1.0",
                "Terms of service",
                new Contact("3t-dev", "", ""),
                "License of API", "", Collections.emptyList());
    }
}
