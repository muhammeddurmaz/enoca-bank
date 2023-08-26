package com.challenge.enocabank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EnocaBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnocaBankApplication.class, args);
    }

}
