package com.dongyang.core.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class ApiResponse<T> {

	private HttpStatusCode statusCode;
	private String message;
	private T data;

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(HttpStatus.OK, "", data);
	}

	public static <T> ApiResponse<T> success(String message, T data) {
		return new ApiResponse<>(HttpStatus.OK, message, data);
	}

	public static <T> ApiResponse<T> success(String message) {
		return new ApiResponse<>(HttpStatus.OK, message, null);
	}
}
