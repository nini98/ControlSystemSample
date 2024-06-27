package com.example.controlsystemsample.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpRequestDTO {
	@NotBlank
	private final String loginId;
	@NotBlank
	private final String password;
	@NotBlank
	private final String email;
	@NotBlank
	private final String phone;
	@NotBlank
	private final String name;
	@NotBlank
	private final String role;
}
