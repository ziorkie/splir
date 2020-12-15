package com.ziora.splir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
public class SplirApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplirApplication.class, args);
    }

}
