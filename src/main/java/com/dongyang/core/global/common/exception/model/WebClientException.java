package com.dongyang.core.global.common.exception.model;

import com.dongyang.core.global.response.ErrorCode;

public class WebClientException extends CustomException {

    public WebClientException(String message, ErrorCode errorCode, Throwable throwable) {
        super(message, errorCode, throwable);
    }
}
