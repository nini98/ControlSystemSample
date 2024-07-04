package com.example.controlsystemsample.model.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserData2ListResponseDTO {
	private final Integer id;
	private final String testColumn1;
	private final String testColumn2;
	private final String testColumn3;
	private final String testColumn4;
	private final String insertDate;
	private final String updateDate;
}
