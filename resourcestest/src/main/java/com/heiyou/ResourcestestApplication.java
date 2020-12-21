package com.heiyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootApplication
public class ResourcestestApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(ResourcestestApplication.class, args);

    }

}
