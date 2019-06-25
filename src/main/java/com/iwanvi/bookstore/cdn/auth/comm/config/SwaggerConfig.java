package com.iwanvi.bookstore.cdn.auth.comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* Swagger配置类
* @author zzw
 * @since 6.3.0
* @date 2018年11月16日16:46:55
*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.iwanvi.bookstore.cdn.auth"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
        return docket;
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("CND鉴权验证")
                .description("CND鉴权验证")
                .termsOfServiceUrl("")
                .contact(new Contact("","",""))
                .license("")
                .licenseUrl("")
                .version("0.0.1")
                .build();
    }
}