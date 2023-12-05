package com.github.zouzdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.github.zouzdc.**"}, exclude = {DataSourceAutoConfiguration.class})
public class TuanZiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuanZiApplication.class, args);
    }





}