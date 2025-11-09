package com.bieliaiev.auth_api.controller;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bieliaiev.auth_api.dto.ProcessRequest;
import com.bieliaiev.auth_api.dto.ProcessResponse;
import com.bieliaiev.auth_api.service.ProcessService;

@RestController
@RequestMapping("/api")
public class ProcessController {

    private final ProcessService service;

    public ProcessController(ProcessService service) {
        this.service = service;
    }

    @PostMapping("/process")
    public ProcessResponse process(@RequestBody ProcessRequest request, Authentication auth) {
        return service.process((UUID)auth.getPrincipal(), request.text());
    }
}