package com.example.controlsystemsample.model.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserData1ListResponseWrapperDTO {
	private final Integer draw;
	private final Integer totalRecords;
	private final List<UserData1ListResponseDTO> data;
}
