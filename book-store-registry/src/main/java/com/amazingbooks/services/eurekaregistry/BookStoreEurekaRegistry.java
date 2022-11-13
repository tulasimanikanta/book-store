package com.amazingbooks.services.eurekaregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BookStoreEurekaRegistry {
    public static void main(String[] args) {
        SpringApplication.run(BookStoreEurekaRegistry.class, args);
    }
}
