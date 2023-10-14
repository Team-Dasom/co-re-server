package com.dongyang.core.global.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

	/*
		200 OK
	 */
	RECOMMEND_VARIABLE_NAME_SUCCESS(HttpStatus.OK, "변수명 추천 요청 성공"),
	ADD_COMMENT_SUCCESS(HttpStatus.OK, "설명 주석 요청 성공"),
	CHANGE_LANGUAGE_SUCCESS(HttpStatus.OK, "언어 변환 요청 성공"),
	REFACTOR_CODE_SUCCESS(HttpStatus.OK, "코드 리팩토링 요청 성공"),
	OAUTH_LOGIN_SUCCESS(HttpStatus.OK, "소셜 OAuth2 로그인 성공"),
	LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃 성공"),
	JWT_TOKEN_REISSUE_SUCCESS(HttpStatus.OK, "JWT 토큰 갱신 성공"),
	;




	private final HttpStatus httpStatus;
	private final String message;

	public int getHttpStatusCode() {
		return httpStatus.value();
	}

}
