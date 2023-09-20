package com.dongyang.core.global.common.exception.model;

public class CustomException extends RuntimeException {

	public CustomException(String message, Exception error) {
		super(message, error);
	}
	public CustomException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public CustomException(String message) {
		super(message);
	}

}
