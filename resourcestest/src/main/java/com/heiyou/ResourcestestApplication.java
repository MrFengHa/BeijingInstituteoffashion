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
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println(path.getAbsolutePath());
//          如果上传的文件为/static/successImage/content/  如下
//        File upload = new File(path.getAbsolutePath(), "static/successImage/content/");
//        if (!upload.exists()) upload.mkdirs();
//        String uploadPath = upload + "\\";
//        file.transferTo(new File( uploadPath+fileName));
    }

}
