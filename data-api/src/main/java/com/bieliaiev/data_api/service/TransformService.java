package com.bieliaiev.data_api.service;

import org.springframework.stereotype.Service;

@Service
public class TransformService {

    public String transform(String text) {
        return new StringBuilder(text)
                .reverse()
                .toString()
                .toUpperCase();
    }
}
