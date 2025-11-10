package com.bieliaiev.data_api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bieliaiev.data_api.service.TransformService;

@RestController
@RequestMapping("/api")
public class TransformController {

    private final TransformService service;

    @Value("${internal.token}")
    private String internalToken;

    public TransformController(TransformService service) {
        this.service = service;
    }

    @PostMapping("/transform")
    public ResponseEntity<Map<String, String>> transform(
            @RequestHeader(value = "X-Internal-Token", required = false) String token,
            @RequestBody Map<String, String> body
    ) {

        if (token == null || !token.equals(internalToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Invalid internal token"));
        }

        String input = body.get("text");
        if (input == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing 'text' field"));
        }

        String result = service.transform(input);
        return ResponseEntity.ok(Map.of("result", result));
    }
}
