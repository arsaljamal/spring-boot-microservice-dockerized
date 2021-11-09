package com.learning.springboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Explore Tour API",
                description = "API Definitions of the Explore Tour Microservice",
                version = "3.0.1"

        ))
public class TourServiceApplication{
    public static void main(String [] args) {
        SpringApplication.run(TourServiceApplication.class, args);
    }
}