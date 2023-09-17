package com.dongyang.core.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class ApiResponse<T> {

	private String message;
	private T data;

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>("", data);
	}

	public static <T> ApiResponse<T> success(String message, T data) {
		return new ApiResponse<>(message, data);
	}


	public static <T> ApiResponse<T> success(String message) {
		return new ApiResponse<>(message, null);
	}
}
