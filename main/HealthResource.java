package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthResource {

    @GetMapping
    //127.0.0.1:8080/health
    public String isHealthy() {
        return "OK";
    }

    @GetMapping("/stats")
    //127.0.0.1:8080/health/stats
    public String getHealthStats() {
        return "OK KO OK KO";
    }
}
