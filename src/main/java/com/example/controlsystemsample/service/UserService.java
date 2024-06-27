package com.example.controlsystemsample.service;

import com.example.controlsystemsample.common.exception.ControlSystemException;
import com.example.controlsystemsample.common.response.ResultCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.controlsystemsample.model.dto.request.SignUpRequestDTO;
import com.example.controlsystemsample.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	public void signup(SignUpRequestDTO params) {
		SignUpRequestDTO newSignUpRequestDTO = new SignUpRequestDTO(
			params.getLoginId(),
			passwordEncoder.encode(params.getPassword()),
			params.getEmail(),
			params.getPhone(),
			params.getName(),
			params.getRole()
		);

		userMapper.signup(newSignUpRequestDTO);
	}
}
