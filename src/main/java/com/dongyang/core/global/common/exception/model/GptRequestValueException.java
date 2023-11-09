package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class GptRequestValueException extends CustomException{

	public GptRequestValueException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}
}
