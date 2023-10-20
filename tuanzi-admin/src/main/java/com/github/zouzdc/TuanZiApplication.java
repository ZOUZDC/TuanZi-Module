package com.github.zouzdc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TuanZiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuanZiApplication.class, args);
    }

    @Value("${tuanzi.version}")
    private String version;

    @Bean
    public void CommandLineRunner(){
       String context = """
                
                ---------------------------------------------
                小团子版本
                %s
                ---------------------------------------------
                """.formatted(version);
        System.out.println(context);
    }


}