package dev.oflouis.example.resilience4j.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Resilience4jFeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(Resilience4jFeignApplication.class, args);
  }

}
