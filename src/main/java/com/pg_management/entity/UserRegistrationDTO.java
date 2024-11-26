package com.pg_management.entity;

import lombok.Data;

@Data
public class UserRegistrationDTO {
	private String name;
    private String email;
    private String password;
    private String role;  // "ADMIN" or "TENANT"
}
