package com.web.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Michael on 2016/6/26.
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class ApplicationSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .build()
            .apiInfo(new ApiInfo(
                "企业微信接入开发接口",
                "这是项目接口说明",
                "1.0",
                "",
//                new Contact("树先森", "", "ymyangshukai@126.com"),
                new Contact("", "", ""),
                "",
                ""
            )).protocols(Collections.singleton("http"));

    }
}
