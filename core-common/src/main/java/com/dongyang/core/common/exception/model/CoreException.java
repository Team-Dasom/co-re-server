package com.dongyang.core.common.exception.model;

public class CoreException extends RuntimeException {

	public CoreException(String message, Exception error) {
		super(message, error);
	}
	public CoreException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public CoreException(String message) {
		super(message);
	}

}
