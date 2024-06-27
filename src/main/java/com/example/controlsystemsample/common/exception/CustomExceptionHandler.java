package com.example.controlsystemsample.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlsystemsample.common.response.Response;
import com.example.controlsystemsample.common.response.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ControlSystemException.class)
	public final ResponseEntity<Response<?>> handleControlSystemException(ControlSystemException controlSystemException) {
		log.error("[handleControlSystemException] : {}", controlSystemException.getMessage());
		Response<?> response = Response.fail(controlSystemException.getResultCode());
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(controlSystemException.getResultCode().getCode()));
		return new ResponseEntity<>(response, httpStatus);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Response<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuilder errorMessage = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
		}
		log.error("[handleValidationExceptions] : {}", errorMessage);
		Response<?> response = Response.fail(ResultCode.VALIDATION_ERROR, errorMessage.toString());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Response<?>> handleAllException(Exception e) {
		log.error("[handleAllException] : ", e);
		Response<?> response = Response.fail(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}