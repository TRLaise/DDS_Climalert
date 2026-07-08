package com.utn.utnclimalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UtnClimalertApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtnClimalertApplication.class, args);
    }
}