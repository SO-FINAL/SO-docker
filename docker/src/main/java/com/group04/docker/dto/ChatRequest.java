package com.group04.docker.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private String sentBy;
    private String message;
}
