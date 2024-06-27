package com.example.controlsystemsample.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IndexResponseDTO {
    private final Integer id;
    private final String email;
    private final String phone;
    private final String password;
    private final String insertDate;
    private final String updateDate;
}
