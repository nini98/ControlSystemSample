package com.example.controlsystemsample.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserData1ListRequestDTO {
	private final String column1;
	private final String column2;
}
