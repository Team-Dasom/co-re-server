package com.dongyang.core.global.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

	/*
		200 OK
	 */
	RECOMMEND_VARIABLE_NAME_SUCCESS(HttpStatus.OK, "변수명 추천 요청에 성공하였습니다."),
	ADD_COMMENT_SUCCESS(HttpStatus.OK, "설명 주석 요청에 성공하였습니다.");




	private final HttpStatus httpStatus;
	private final String message;

	public int getHttpStatusCode() {
		return httpStatus.value();
	}

	public String getMessage() {
		return message;
	}
}
