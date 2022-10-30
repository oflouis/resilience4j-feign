package dev.oflouis.example.resilience4j.feign;

import feign.Param;
import feign.RequestLine;

public interface HttpBinAPI {
  @RequestLine("GET /get")
  GetResponse get();

  @RequestLine("POST /delay/{delay}")
  GetResponse delay(@Param("delay") Integer delay);

  @RequestLine("PUT /status/{codes}")
  String status(@Param("codes") Integer codes);
}
