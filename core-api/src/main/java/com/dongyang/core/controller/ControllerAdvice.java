package com.dongyang.core.controller;

import static com.dongyang.core.common.constants.message.ControllerAdviceErrorMessage.*;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.dongyang.core.common.exception.model.BadGatewayException;
import com.dongyang.core.common.exception.model.ConflictException;
import com.dongyang.core.common.exception.model.ForbiddenException;
import com.dongyang.core.common.exception.model.NotFoundException;
import com.dongyang.core.common.exception.model.ValidationException;
import com.dongyang.core.dto.ApiResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	protected ApiResponse<Object> handleBadRequest(BindException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.BAD_REQUEST, VALIDATION_ERROR_MESSAGE,
			Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.BAD_REQUEST, VALIDATION_ERROR_MESSAGE,
			Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ApiResponse<Object> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.BAD_REQUEST, VALIDATION_ERROR_MESSAGE,
			Objects.requireNonNull(exception.getMessage()));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		HttpMessageNotReadableException.class,
		InvalidFormatException.class,
		ServletRequestBindingException.class
	})
	protected ApiResponse<Object> handleInvalidFormatException(final Exception exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.BAD_REQUEST, VALIDATION_ERROR_MESSAGE);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	protected ApiResponse<Object> handleValidationException(final ValidationException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.BAD_REQUEST, VALIDATION_ERROR_MESSAGE);
	}

	/**
	 * 403 Forbidden
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(ForbiddenException.class)
	protected ApiResponse<Object> handleForbiddenException(final ForbiddenException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.FORBIDDEN, FORBIDDEN_ERROR_MESSAGE);
	}

	/**
	 * 404 NotFound
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	protected ApiResponse<Object> handleNotFoundException(final NotFoundException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.NOT_FOUND, NOT_FOUND_ERROR_MESSAGE);
	}

	/**
	 * 405 Method Not Supported
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ApiResponse<Object> handleHttpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException exception) {
		return ApiResponse.error(HttpStatus.METHOD_NOT_ALLOWED, METHOD_NOT_ALLOWED_ERROR_MESSAGE);
	}

	/**
	 * 409 Conflict
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ConflictException.class)
	protected ApiResponse<Object> handleConflictException(final ConflictException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.CONFLICT, CONFLICT_ERROR_MESSAGE);
	}

	/**
	 * 415 UnSupported Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeException.class)
	protected ApiResponse<Object> handleHttpMediaTypeException(final HttpMediaTypeException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE, UNSUPPORTED_MEDIA_TYPE_ERROR_MESSAGE);
	}

	/**
	 * 502 Bad Gateway
	 */
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(BadGatewayException.class)
	protected ApiResponse<Object> handleBadGatewayException(final BadGatewayException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.BAD_GATEWAY, BAD_GATEWAY_ERROR_MESSAGE);
	}

	/**
	 * 500 Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected ApiResponse<Object> handleException(final Exception exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
	}
}
