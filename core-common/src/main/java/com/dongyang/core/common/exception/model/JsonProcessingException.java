package com.dongyang.core.common.exception.model;

public class JsonProcessingException extends CoreException {

	public JsonProcessingException(String message, Exception e) {
		super(message, e);
	}

	public JsonProcessingException(String message) {
		super(message);
	}

}