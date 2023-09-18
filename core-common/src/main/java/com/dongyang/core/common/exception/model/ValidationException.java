package com.dongyang.core.common.exception.model;

public class ValidationException extends CoreException {

	public ValidationException(String message, Exception e) {
		super(message, e);
	}

	public ValidationException(String message) {
		super(message);
	}

}
