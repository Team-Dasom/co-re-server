package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class NotFoundException extends CustomException {

	public NotFoundException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}
}
