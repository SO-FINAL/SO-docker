package com.group04.docker.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
