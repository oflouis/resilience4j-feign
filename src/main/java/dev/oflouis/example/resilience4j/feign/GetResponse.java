package dev.oflouis.example.resilience4j.feign;

import lombok.Getter;

@Getter
public class GetResponse {
  private String origin;
  private String url;
  private GetResponseHeader headers;
}
