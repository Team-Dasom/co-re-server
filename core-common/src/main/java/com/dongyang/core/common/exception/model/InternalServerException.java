package com.dongyang.core.common.exception.model;

public class InternalServerException extends CoreException {

	public InternalServerException(String message, Exception e) {
		super(message, e);
	}

	public InternalServerException(String message) {
		super(message);
	}

}
