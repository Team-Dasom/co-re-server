package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class IllegalArgumentException extends CustomException {
    public IllegalArgumentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
