package com.iwanvi.bookstore.cdn.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.iwanvi.bookstore.cdn.auth"})
@EnableSwagger2
@EnableTransactionManagement
@EnableEurekaClient
@EnableScheduling // 启动spring schedule
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("----------------system beg----------------");
        SpringApplication.run(Application.class, args);
        LOGGER.info("----------------system end----------------");
    }
}
