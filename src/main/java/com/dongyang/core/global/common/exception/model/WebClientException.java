package com.dongyang.core.global.common.exception.model;

public class WebClientException extends CustomException {

	public WebClientException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public WebClientException(String message) {
		super(message);
	}

}
