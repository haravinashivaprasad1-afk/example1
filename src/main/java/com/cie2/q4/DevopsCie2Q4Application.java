package com.cie2.q4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class DevopsCie2Q4Application {

    public static void main(String[] args) {
        SpringApplication.run(DevopsCie2Q4Application.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "<h1>CI/CD Pipeline - Kubernetes with Cron Trigger (Q4)</h1>";
    }
}
