package com.example.controlsystemsample.common.response;

public enum ResultCode {
	SUCCESS("200", "성공"),
	NO_USER("404", "존재하지 않는 사용자입니다."),
	PASSWORD_MISMATCH("401", "비밀번호가 일치하지 않습니다."),
	BLOCKED_USER("403", "비밀번호를 5회 이상 틀려 차단된 사용자입니다."),
	NO_LOGIN_USER("401", "로그인이 필요합니다."),
	VALIDATION_ERROR("400", "입력한 값이 유효하지 않습니다. 필수 항목을 확인해 주세요."),
	INTERNAL_SERVER_ERROR("500", "서버 에러 (관리자에게 연락하시기 바랍니다.)");

	private final String code;
	private final String message;

	ResultCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}
}
