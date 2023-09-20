package com.dongyang.core.global.common.exception.model;

public class ValidationException extends CustomException {

	public ValidationException(String message, Exception e) {
		super(message, e);
	}

	public ValidationException(String message) {
		super(message);
	}

}
