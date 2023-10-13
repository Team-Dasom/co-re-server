package com.dongyang.core.global.response;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
	/*
		400 - BAD REQUEST
	 */
	REQUEST_VALIDATION_EXCEPTION(BAD_REQUEST, "잘못된 요청입니다."),
	BIND_EXCEPTION(BAD_REQUEST, "요청 값을 바인딩하는 과정에서 오류가 발생하였습니다."),
	METHOD_ARGUMENT_NOT_VALID_EXCEPTION(BAD_REQUEST, "요청 값이 검증되지 않은 값 입니다."),
	VALIDATION_EXCEPTION(BAD_REQUEST, "검증되지 않은 값 입니다."),
	METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(BAD_REQUEST, "요청 값의 타입이 잘못되었습니다."),
	INVALID_FORMAT_EXCEPTION(BAD_REQUEST, "요청 값이 유효하지 않은 데이터입니다."),

	/*
		403 - FORBIDDEN
	 */
	FORBIDDEN_EXCEPTION(FORBIDDEN, "해당 요청에 대한 요청 권한이 존재하지 않습니다."),

	/*
		404 - METHOD NOT ALLOWED
	 */
	NOT_FOUND_EXCEPTION(NOT_FOUND, "존재하지 않는 요청입니다."),

	/*
		405 - METHOD NOT ALLOWED
	 */
	METHOD_NOT_ALLOWED_EXCEPTION(METHOD_NOT_ALLOWED, "잘못된 HTTP Method 요청입니다."),

	/*
		409 - CONFLICT
	*/
	CONFLICT_EXCEPTION(CONFLICT, "데이터를 처리하는 과정에서 충돌이 발생하였습니다."),

	/*
		415 - UN SUPPORTED MEDIA TYPE
	*/
	UN_SUPPORTED_MEDIA_TYPE_EXCEPTION(UNSUPPORTED_MEDIA_TYPE, "지원하지 않는 미디어 타입입니다."),

	/*
		500 - INTERNAL SERVER ERROR
	*/
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다."),

	/*
		502- BAD GATEWAY
	 */
	BAD_GATEWAY_ERROR(BAD_GATEWAY, "외부 연동 중 에러가 발생하였습니다."),

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
