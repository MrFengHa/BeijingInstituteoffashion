package com.heiyou.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/21 1:42
 */
@ComponentScan
@Configuration
public class WebConfigurer extends WebMvcConfigurationSupport {
    @Value("${myserviceres.path}")
    String serviceResPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径

        registry.addResourceHandler("/**").addResourceLocations("file:"+ serviceResPath).addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
