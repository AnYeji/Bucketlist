package com.rsupport.bucketlist.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.rsupport.bucketlist")
@EntityScan("com.rsupport.bucketlist.core.domain")
@EnableJpaRepositories("com.rsupport.bucketlist.core.repository")
public class AdminApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(AdminApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(AdminApplication.class, args);
  }
}
