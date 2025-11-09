package com.bieliaiev.auth_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bieliaiev.auth_api.entity.ProcessingLog;

public interface ProcessingLogRepository extends JpaRepository<ProcessingLog, UUID> {

}
