package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class RateLimitException extends CustomException{

	public RateLimitException(ErrorCode errorCode) {
		super(errorCode);
	}
}
