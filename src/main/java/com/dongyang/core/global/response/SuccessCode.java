package com.dongyang.core.global.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

	/*
		200 OK
	 */
	RECOMMEND_VARIABLE_NAME_SUCCESS(HttpStatus.OK, "변수명 추천 요청 성공"),
	ADD_COMMENT_SUCCESS(HttpStatus.OK, "설명 주석 요청 성공"),
	KAKAO_LOGIN_SUCCESS(HttpStatus.OK, "카카오 로그인 성공"),
	LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃 성공"),
	JWT_TOKEN_REISSUE_SUCCESS(HttpStatus.OK, "JWT 토큰 갱신 성공"),
	;




	private final HttpStatus httpStatus;
	private final String message;

	public int getHttpStatusCode() {
		return httpStatus.value();
	}

	public String getMessage() {
		return message;
	}
}