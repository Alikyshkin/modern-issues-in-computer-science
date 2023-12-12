package com.miics.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@ComponentScan(basePackages = {"com.miics.server.service.implementation", "com.miics.server.controller", "com.miics.server.mappers", "com.miics.server.dao.repositories"})
//@EnableJpaRepositories(basePackages = {"com.miics.server.repositories"})
//@EnableAutoConfiguration
//@EntityScan(basePackages = {"com.miics.server.models"})
@EnableWebMvc
@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
