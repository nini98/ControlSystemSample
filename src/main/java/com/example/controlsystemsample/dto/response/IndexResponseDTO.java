package com.example.controlsystemsample.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IndexResponseDTO {
    private final String testColumn1;
    private final Integer testColumn2;
}
