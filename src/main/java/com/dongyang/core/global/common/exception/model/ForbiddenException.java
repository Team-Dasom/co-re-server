package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class ForbiddenException extends CustomException {

	public ForbiddenException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}
}
