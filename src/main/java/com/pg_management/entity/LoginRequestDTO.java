package com.pg_management.entity;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
    private String role;
}
