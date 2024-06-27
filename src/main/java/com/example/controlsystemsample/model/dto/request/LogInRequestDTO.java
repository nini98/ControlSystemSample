package com.example.controlsystemsample.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LogInRequestDTO {
	@NotBlank
	private final String loginId;
	@NotBlank
	private final String password;
}
