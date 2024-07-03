package com.example.controlsystemsample.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserData1ListRequestDTO {
	private final String column1;
	private final String column2;

	// 아래 3개의 필드는 DataTable의 serverSide 기능을 활성화 시키면 자동으로 넘어오는 파라미터
	// DB에서 데이터를 가져올 때 서버 사이드 방식으로 가져오려면 아래와 같이 3개의 필드를 정의해야 함
	@NotNull
	private final Integer draw;
	@NotNull
	private final Integer start;
	@NotNull
	private final Integer length;
}
