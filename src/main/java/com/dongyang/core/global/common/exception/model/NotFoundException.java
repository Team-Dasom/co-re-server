package com.dongyang.core.global.common.exception.model;

public class NotFoundException extends CustomException {

	public NotFoundException(String message, Exception e) {
		super(message, e);
	}

	public NotFoundException(String message) {
		super(message);
	}

}
