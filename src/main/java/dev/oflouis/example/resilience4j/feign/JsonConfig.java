package dev.oflouis.example.resilience4j.feign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> {
      builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      builder.featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
      builder.serializationInclusion(JsonInclude.Include.NON_NULL);
      builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
      builder.propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    };
  }
}
