package com.skinmarket.project.dto;

public record ErrorResponseDTO(
        String message,
        int status
) { }
