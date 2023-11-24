package com.dongyang.core.global.common.exception.model;

public class JsonProcessingException extends CustomException {

	public JsonProcessingException(String message, Exception e) {
		super(message, e);
	}

	public JsonProcessingException(String message) {
		super(message);
	}
}
