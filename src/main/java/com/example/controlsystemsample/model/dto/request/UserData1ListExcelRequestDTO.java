package com.example.controlsystemsample.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserData1ListExcelRequestDTO {
	private final String column1;
	private final String column2;
	@NotBlank
	private final String excelHeader;
	@NotBlank
	private final String fileName;
}
