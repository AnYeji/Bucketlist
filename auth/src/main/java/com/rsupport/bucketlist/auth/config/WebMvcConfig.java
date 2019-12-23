package com.rsupport.bucketlist.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Value("${storage.path}")
  private String storagePath;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    /*registry.
            addResourceHandler("/swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
    registry.
            addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");*/
    registry.
            addResourceHandler("/**")
            .addResourceLocations("file:///" + storagePath + "/");
    registry
            .addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");
  }
}
