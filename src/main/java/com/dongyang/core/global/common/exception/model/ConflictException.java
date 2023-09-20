package com.dongyang.core.global.common.exception.model;

public class ConflictException extends CustomException {

	public ConflictException(String message, Exception e) {
		super(message, e);
	}

	public ConflictException(String message) {
		super(message);
	}

}
