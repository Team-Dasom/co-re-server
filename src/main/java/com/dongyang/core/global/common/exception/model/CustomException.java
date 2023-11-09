package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class CustomException extends RuntimeException {
    private ErrorCode errorCode;

    public CustomException(String message, Exception error) {
        super(message, error);
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CustomException(String message, ErrorCode errorCode, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    public CustomException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(String message) {
        super(message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
