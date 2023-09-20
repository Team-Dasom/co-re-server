package com.dongyang.core.common.exception.model;

public class CoreException extends RuntimeException {

	public CoreException(String message, Exception e) {
		super(message, e);
	}

	public CoreException(String message) {
		super(message);
	}

}
