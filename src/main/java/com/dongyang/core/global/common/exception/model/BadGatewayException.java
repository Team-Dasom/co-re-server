package com.dongyang.core.global.common.exception.model;

public class BadGatewayException extends CustomException {

	public BadGatewayException(String message, Exception e) {
		super(message, e);
	}

	public BadGatewayException(String message) {
		super(message);
	}

}
