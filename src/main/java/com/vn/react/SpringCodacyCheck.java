package com.vn.react;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
public class SpringCodacyCheck {
    public static void main(final String[] args) {
        SpringApplication.run(SpringCodacyCheck.class, args);
    }

}