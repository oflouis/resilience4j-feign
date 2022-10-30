package dev.oflouis.example.resilience4j.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
@RequiredArgsConstructor
public class RestTestController {
  private final RestTestService restTestService;

  @RequestMapping("/get")
  public String get() {
    return restTestService.get();
  }


  @RequestMapping("/delay")
  public String delay() {
    return restTestService.delay();
  }

  @RequestMapping("/error")
  public String status() {
    return restTestService.error();
  }
}
