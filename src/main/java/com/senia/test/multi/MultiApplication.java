package com.senia.test.multi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MultiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiApplication.class, args);
    }

}
