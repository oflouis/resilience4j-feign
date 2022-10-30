# Resilience4j Feign Example

# How to test

- Prepare an external system and a test application
```
# Use httpbin as external system
docker pull kennethreitz/httpbin
docker run -p 80:80 kennethreitz/httpbin

# Get this repository and execute the application
git clone https://github.com/oflouis/resilience4j-feign.git
cd resilience4j-feign
./gradlew clean bootRun 
```

- Test Command
```
# Circuit Breaker w/ Failure
for i in $(seq 1 4); do \
  curl "http://localhost:8080/v1/test/error"; \
  curl "http://localhost:8080/v1/test/error"; \
  curl "http://localhost:8080/v1/test/get"; \
done

# Circuit Breaker w/ Delay
for i in $(seq 1 10); do curl "http://localhost:8080/v1/test/delay"; done

# Rate Limiter
for i in $(seq 1 30); do curl "http://localhost:8080/v1/test/get"; done
```

# Links

- httpbin github : https://github.com/postmanlabs/httpbin
- Blog Post : https://oflouis.dev/sw_development/spring-boot/resilience4j-feign-http-client/
