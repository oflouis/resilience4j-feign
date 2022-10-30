package dev.oflouis.example.resilience4j.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.jackson.JacksonDecoder;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RestClientConfig {
  private static final String HTTP_BIN_NAME = "httpbin";
  private static final String HTTP_BIN_URL = "http://localhost:80/";

  private final CircuitBreakerRegistry circuitBreakerRegistry;
  private final RateLimiterRegistry rateLimiterRegistry;
  private final OkHttpClient okHttpClient;
  private final ObjectMapper objectMapper;

  @Bean
  public HttpBinAPI app2AppAPIClient() {
    CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(HTTP_BIN_NAME);
    circuitBreaker.getEventPublisher()
        .onReset(event -> log.warn("CircuitBreaker > onReset > {}", event))
        .onError(event -> log.warn("CircuitBreaker > onError > {}", event))
        .onFailureRateExceeded(event -> log.warn("CircuitBreaker > onSlowCallRateExceeded > {}", event))
        .onSlowCallRateExceeded(event -> log.warn("CircuitBreaker > onSlowCallRateExceeded > {}", event))
        .onStateTransition(event -> log.warn("CircuitBreaker > onStateTransition > {}", event));

    RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(HTTP_BIN_NAME);
    rateLimiter.getEventPublisher()
        .onFailure(event -> log.warn("RateLimiter > onFailure > {}", event));

    FeignDecorators feignDecorators = FeignDecorators.builder()
        .withCircuitBreaker(circuitBreaker)
        .withRateLimiter(rateLimiter)
        .build();

    return Resilience4jFeign.builder(feignDecorators)
        .client(new feign.okhttp.OkHttpClient(okHttpClient))
        .decoder(new JacksonDecoder(objectMapper))
        .target(HttpBinAPI.class, HTTP_BIN_URL);
  }
}
