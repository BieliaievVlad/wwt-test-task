	package com.bieliaiev.auth_api.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "users", schema = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
}
