package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class ConflictException extends CustomException {

    public ConflictException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
