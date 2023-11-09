package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class UnAuthorizedException extends CustomException{

	public UnAuthorizedException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}
}
