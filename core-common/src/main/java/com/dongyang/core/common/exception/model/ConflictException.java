package com.dongyang.core.common.exception.model;

public class ConflictException extends CoreException {

	public ConflictException(String message, Exception e) {
		super(message, e);
	}

	public ConflictException(String message) {
		super(message);
	}

}
