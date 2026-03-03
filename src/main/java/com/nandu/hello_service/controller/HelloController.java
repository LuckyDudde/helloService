package com.nandu.hello_service.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Hello from Jenkins Pipeline!",
                "status",  "UP",
                "version", "1.0"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    @PostMapping("/greet")
    public Map<String, String> greet(@RequestBody Map<String, String> body) {
        String name = body.getOrDefault("name", "World");
        return Map.of("greeting", "Hello, " + name + "!");
    }
}