package com.dongyang.core.dto;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class ApiResponse<T> {

	private HttpStatus status;
	private String message;
	private T data;

	public static <T> ApiResponse<T> success(HttpStatus status, T data) {
		return new ApiResponse<>(status, "", data);
	}

	public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
		return new ApiResponse<>(status, message, data);
	}

	public static <T> ApiResponse<T> success(HttpStatus status, String message) {
		return new ApiResponse<>(status, message, null);
	}

	public static <T> ApiResponse<T> error(HttpStatus status, String message) {
		return new ApiResponse<>(status, message, null);
	}

	public static <T> ApiResponse<T> error(HttpStatus status, String message, T data) {
		return new ApiResponse<>(status, message, data);
	}
}
