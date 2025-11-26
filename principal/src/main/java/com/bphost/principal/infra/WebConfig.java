package com.bphost.principal.infra;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/icon/**")
                .addResourceLocations("classpath:/static/icon/");
        
        registry.addResourceHandler("/products/**")
                .addResourceLocations("file:./uploadImage/products/");
    }
}
