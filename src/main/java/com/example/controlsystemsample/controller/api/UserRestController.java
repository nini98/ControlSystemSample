package com.example.controlsystemsample.controller.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlsystemsample.common.response.Response;
import com.example.controlsystemsample.model.dto.request.SignUpRequestDTO;
import com.example.controlsystemsample.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {
	private final UserService userService;
	@PostMapping("/signup")
	public Response<?> signup(@Valid @RequestBody SignUpRequestDTO params) throws Exception {
		userService.signup(params);
		return Response.success();
	}
}
