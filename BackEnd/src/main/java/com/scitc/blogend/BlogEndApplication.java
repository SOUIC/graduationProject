package com.scitc.blogend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.scitc.blogend.mapper")
public class BlogEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogEndApplication.class, args);
    }

}
