package com.dongyang.core.global.common.exception.model;

public class InternalServerException extends CustomException {

	public InternalServerException(String message, Exception e) {
		super(message, e);
	}

	public InternalServerException(String message) {
		super(message);
	}

}
