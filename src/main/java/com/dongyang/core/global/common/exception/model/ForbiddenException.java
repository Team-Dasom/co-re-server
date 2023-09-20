package com.dongyang.core.global.common.exception.model;

public class ForbiddenException extends CustomException {

	public ForbiddenException(String message, Exception e) {
		super(message, e);
	}

	public ForbiddenException(String message) {
		super(message);
	}

}
