package com.example.controlsystemsample.model;

import com.example.controlsystemsample.enums.UserRole;

import com.example.controlsystemsample.enums.UserStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
	private int id;
	private String loginId;
	private String password;
	private String email;
	private String phone;
	private String name;
	private UserRole role;
	private UserStatus status;
	private int loginTryCnt;
	private String blockYn;
}
