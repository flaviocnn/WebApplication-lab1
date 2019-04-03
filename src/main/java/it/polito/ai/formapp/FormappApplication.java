package it.polito.ai.formapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class FormappApplication {

    @Bean
    @Scope("singleton")
    ConcurrentHashMap<String,Users> users() {
        return new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {
        SpringApplication.run(FormappApplication.class, args);
    }

}
