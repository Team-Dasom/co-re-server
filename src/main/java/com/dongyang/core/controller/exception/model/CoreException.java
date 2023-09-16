package com.dongyang.core.controller.exception.model;

public class CoreException extends RuntimeException {

	public CoreException(String message, Exception e) {
		super(message, e);
	}

}
