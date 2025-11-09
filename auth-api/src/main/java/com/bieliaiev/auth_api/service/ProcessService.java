package com.bieliaiev.auth_api.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bieliaiev.auth_api.dto.ProcessResponse;
import com.bieliaiev.auth_api.entity.ProcessingLog;
import com.bieliaiev.auth_api.entity.User;
import com.bieliaiev.auth_api.repository.ProcessingLogRepository;
import com.bieliaiev.auth_api.repository.UserRepository;

@Service
public class ProcessService {
	
	@Value("${INTERNAL_TOKEN}")
    private String internalToken;
	private final ProcessingLogRepository repository;
	private final UserRepository userRepository;
	private final RestTemplate restTemplate;
	
	public ProcessService (ProcessingLogRepository repository, UserRepository userRepository, RestTemplate restTemplate) {
		this.repository = repository;
		this.userRepository = userRepository;
		this.restTemplate = restTemplate;
	}

    public ProcessResponse process(UUID userId, String text) {
    	
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String result = callDataApi(text);

        ProcessingLog log = new ProcessingLog(
                null,
                user,
                text,
                result,
                LocalDateTime.now()
        );
        repository.save(log);

        return new ProcessResponse(result);
    }
    
    private HttpEntity<Map<String, String>> buildRequest(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Internal-Token", internalToken);

        return new HttpEntity<>(Map.of("text", text), headers);
    }
    
    private String callDataApi(String text) {
        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                "http://data-api:8081/api/transform",
                HttpMethod.POST,
                buildRequest(text),
                new ParameterizedTypeReference<>() {}
        );

        Map<String, String> body = response.getBody();
        if (body == null || !body.containsKey("result")) {
            throw new RuntimeException("Invalid response from data-api");
        }

        return body.get("result");
    }
}
