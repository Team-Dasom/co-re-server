package com.dongyang.core.common.exception.model;

public class NotFoundException extends CoreException {

	public NotFoundException(String message, Exception e) {
		super(message, e);
	}

	public NotFoundException(String message) {
		super(message);
	}

}
