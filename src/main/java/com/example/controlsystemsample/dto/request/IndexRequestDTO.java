package com.example.controlsystemsample.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class IndexRequestDTO {
    @NotBlank
    private final String email;
    @NotNull
    private final Integer id;
}
