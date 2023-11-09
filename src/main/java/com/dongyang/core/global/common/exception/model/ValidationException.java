package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class ValidationException extends CustomException {

	public ValidationException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}
}
