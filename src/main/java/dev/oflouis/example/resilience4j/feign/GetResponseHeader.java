package dev.oflouis.example.resilience4j.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetResponseHeader {
  private String accept;
  @JsonProperty("accept-encoding")
  private String acceptEncoding;
  @JsonProperty("accept-language")
  private String acceptLanguage;
  @JsonProperty("user-agent")
  private String userAgent;
}
