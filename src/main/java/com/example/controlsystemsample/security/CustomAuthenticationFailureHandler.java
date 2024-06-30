package com.example.controlsystemsample.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.controlsystemsample.common.response.Response;
import com.example.controlsystemsample.common.response.ResultCode;
import com.example.controlsystemsample.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final UserMapper userMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws
		IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=UTF-8");
		ResultCode resultCode;
		String loginId = request.getParameter("loginId");
		userMapper.updateLoginTryCnt(loginId);

		if (exception.getCause() instanceof LockedException) {
			resultCode = ResultCode.BLOCKED_USER;
		} else if (exception instanceof UsernameNotFoundException) {
			resultCode = ResultCode.NO_USER;
		} else if (exception instanceof BadCredentialsException) {
			resultCode = ResultCode.PASSWORD_MISMATCH;
		} else if (exception instanceof InternalAuthenticationServiceException) {
			resultCode = ResultCode.INTERNAL_SERVER_ERROR;
		} else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			resultCode = ResultCode.INTERNAL_SERVER_ERROR;
		} else {
			resultCode = ResultCode.INTERNAL_SERVER_ERROR;
		}

		// 여기에서 throw new ControlSystemException(ResultCode.NO_USER); 를 하지 않고 직접 Response를 하는 이유는
		// 그 예외가 Spring Security의 필터 체인 내부에서 처리되기 때문에 Spring MVC의 @ExceptionHandler 메소드가 해당 예외를 감지하지 못하기 때문
		Response<?> errorResponse = Response.fail(resultCode);
		String jsonResponse = objectMapper.writeValueAsString(errorResponse);
		response.getWriter().write(jsonResponse);
	}
}
