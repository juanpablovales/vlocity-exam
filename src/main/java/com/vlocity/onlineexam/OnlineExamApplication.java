package com.vlocity.onlineexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.vlocity")
@EnableJpaRepositories(basePackages = "com.vlocity.onlineexam.repository")
@EnableAutoConfiguration
public class OnlineExamApplication {

  public static void main(String[] args) {
    SpringApplication.run(OnlineExamApplication.class, args);
  }
}
