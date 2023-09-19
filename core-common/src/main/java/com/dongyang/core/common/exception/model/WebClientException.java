package com.dongyang.core.common.exception.model;

public class WebClientException extends CoreException {

	public WebClientException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public WebClientException(String message) {
		super(message);
	}

}
