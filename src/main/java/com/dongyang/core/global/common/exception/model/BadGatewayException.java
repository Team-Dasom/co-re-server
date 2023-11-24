package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class BadGatewayException extends CustomException {

	public BadGatewayException(String message, Exception e) {
		super(message, e);
	}

	public BadGatewayException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}
}
