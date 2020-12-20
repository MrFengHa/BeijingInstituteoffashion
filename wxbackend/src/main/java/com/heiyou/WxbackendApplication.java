package com.heiyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import reactor.core.publisher.SynchronousSink;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootApplication
@MapperScan("com.heiyou.mapper")
public class WxbackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxbackendApplication.class, args);
        System.out.println(System.getProperty("user.dir"));
    }

}
