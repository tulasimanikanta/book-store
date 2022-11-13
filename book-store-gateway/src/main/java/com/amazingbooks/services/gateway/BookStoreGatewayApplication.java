package com.amazingbooks.services.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookStoreGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookStoreGatewayApplication.class, args);
    }
}
