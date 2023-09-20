package com.dongyang.core.global.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

	private final int status;
	private final String message;
	private T data;

	public static <T> ApiResponse<T> success(SuccessCode successCode, T data) {
		return new ApiResponse<>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
	}

	public static <T> ApiResponse<T> success(SuccessCode successCode) {
		return new ApiResponse<>(successCode.getHttpStatusCode(), successCode.getMessage(), null);
	}

	public static <T> ApiResponse<T> error(ErrorCode errorCode) {
		return new ApiResponse<>(errorCode.getHttpStatusCode(), errorCode.getMessage());
	}

	public static <T> ApiResponse<T> error(ErrorCode errorCode, String message) {
		return new ApiResponse<>(errorCode.getHttpStatusCode(), message);
	}

	public static <T> ApiResponse<T> error(ErrorCode errorCode, String message, T data) {
		return new ApiResponse<>(errorCode.getHttpStatusCode(), message, data);
	}
}
