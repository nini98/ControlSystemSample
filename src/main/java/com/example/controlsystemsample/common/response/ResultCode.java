package com.example.controlsystemsample.common.response;

public enum ResultCode {
	SUCCESS("200", "success"),
	NO_USER("601", "user not exist"),
	PASSWORD_MISMATCH("602", "password mismatch"),
	NO_LOGIN_USER("603", "no login user"),
	INTERNAL_SERVER_ERROR("500", "internal server problem (please contact administrator)");

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
