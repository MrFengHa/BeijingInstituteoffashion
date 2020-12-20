package com.heiyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.heiyou.mapper")
public class WxbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxbackendApplication.class, args);
    }

}
