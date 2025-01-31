package com.microservices.currency_exchange_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CicuitBreakerController {
	Logger log = LoggerFactory.getLogger(CicuitBreakerController.class);

	@GetMapping("/getbreaker")
	//@CircuitBreaker(name = "default", fallbackMethod = "fallbackResponse")
	@RateLimiter(name="sample-api")
	public String circuitBreaker() {
		log.info("=====================================SAMPE API TRIGGERED=====================================");
		//ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/sample", String.class);

		return "sample-api";
	}

	public String fallbackResponse(Exception ex) {
		return "fallback-response";
	}
}
