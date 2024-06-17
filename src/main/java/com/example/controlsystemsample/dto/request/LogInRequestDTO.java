package com.example.controlsystemsample.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LogInRequestDTO {
	@NotBlank
	private final String email;
	@NotBlank
	private final String password;
}
