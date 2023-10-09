package com.scitc.blogend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**").addResourceLocations("file:/Users/liuruichao/Program/JAVA/blog_resources/avatar/");
        super.addResourceHandlers(registry);

        registry.addResourceHandler("/blog_img/**").addResourceLocations("file:/Users/liuruichao/Program/JAVA/blog_resources/blog_img/");
        super.addResourceHandlers(registry);
    }
}
