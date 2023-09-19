package com.dongyang.core.common.exception.model;

public class ForbiddenException extends CoreException {

	public ForbiddenException(String message, Exception e) {
		super(message, e);
	}

	public ForbiddenException(String message) {
		super(message);
	}

}
