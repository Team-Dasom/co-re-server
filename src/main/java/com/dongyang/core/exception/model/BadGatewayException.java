package com.dongyang.core.exception.model;

public class BadGatewayException extends CoreException {

	public BadGatewayException(String message, Exception e) {
		super(message, e);
	}

	public BadGatewayException(String message) {
		super(message);
	}

}
