package com.bieliaiev.auth_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bieliaiev.auth_api.dto.LoginRequest;
import com.bieliaiev.auth_api.dto.RegisterRequest;
import com.bieliaiev.auth_api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService service;
	
	public AuthController(AuthService service) {
		this.service = service;
	}
	
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        service.register(request.email(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = service.login(request.email(), request.password());
        return ResponseEntity.ok().body(java.util.Map.of("token", token)
        );
    }
}
