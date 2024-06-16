package com.example.controlsystemsample.common.exception;

import com.example.controlsystemsample.common.response.ResultCode;

import lombok.Getter;

@Getter
public class ControlSystemException extends RuntimeException{
	private ResultCode resultCode;

	public ControlSystemException(ResultCode resultCode) {
		super(resultCode.getMessage());
		this.resultCode = resultCode;
	}
}
