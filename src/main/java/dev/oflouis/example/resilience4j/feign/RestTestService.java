package dev.oflouis.example.resilience4j.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestTestService {
  private final HttpBinAPI httpBinAPI;

  public String get() {
    httpBinAPI.get();
    return "success\n";
  }

  public String delay() {
    httpBinAPI.delay(4);
    return "delay\n";
  }

  public String error() {
    httpBinAPI.status(500);
    return "error\n";
  }
}
