package com.heiyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@SpringBootApplication
public class ResourcestestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourcestestApplication.class, args);
        try {
            System.out.println(ResourceUtils.getURL("classpath:"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
