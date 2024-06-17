package com.example.controlsystemsample.model;

import com.example.controlsystemsample.enums.UserRole;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private UserRole userRole;
}
