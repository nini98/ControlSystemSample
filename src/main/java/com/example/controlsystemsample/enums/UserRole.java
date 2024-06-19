package com.example.controlsystemsample.enums;

public enum UserRole {
	ADMIN("0", "관리자"),
	USER("1", "일반 사용자"),
	GUEST("2", "손님");

	private final String code;
	private final String description;

	UserRole(String code, String description){
		this.code = code;
		this.description = description;
	}

	public String getCode(){return this.code;}
	public String getDescription(){return this.description;}

}
