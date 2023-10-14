package com.dongyang.core.global;

import static com.dongyang.core.global.response.ErrorCode.*;

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

import com.dongyang.core.global.common.exception.model.BadGatewayException;
import com.dongyang.core.global.common.exception.model.ConflictException;
import com.dongyang.core.global.common.exception.model.ForbiddenException;
import com.dongyang.core.global.common.exception.model.GptRequestValueException;
import com.dongyang.core.global.common.exception.model.NotFoundException;
import com.dongyang.core.global.common.exception.model.ValidationException;
import com.dongyang.core.global.common.exception.model.WebClientException;
import com.dongyang.core.global.response.ApiResponse;
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
		return ApiResponse.error(BIND_EXCEPTION,
			Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(METHOD_ARGUMENT_NOT_VALID_EXCEPTION,
			Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ApiResponse<Object> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION,
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
		return ApiResponse.error(INVALID_FORMAT_EXCEPTION);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	protected ApiResponse<Object> handleValidationException(final ValidationException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(VALIDATION_EXCEPTION);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(GptRequestValueException.class)
	protected ApiResponse<Object> handleGptRequestValueException(final GptRequestValueException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(GPT_REQUEST_VALUE_EXCEPTION);
	}

	/**
	 * 403 Forbidden
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(ForbiddenException.class)
	protected ApiResponse<Object> handleForbiddenException(final ForbiddenException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(FORBIDDEN_EXCEPTION);
	}

	/**
	 * 404 NotFound
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	protected ApiResponse<Object> handleNotFoundException(final NotFoundException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(NOT_FOUND_EXCEPTION);
	}

	/**
	 * 405 Method Not Supported
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ApiResponse<Object> handleHttpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException exception) {
		return ApiResponse.error(METHOD_NOT_ALLOWED_EXCEPTION);
	}

	/**
	 * 409 Conflict
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ConflictException.class)
	protected ApiResponse<Object> handleConflictException(final ConflictException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(CONFLICT_EXCEPTION);
	}

	/**
	 * 415 UnSupported Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeException.class)
	protected ApiResponse<Object> handleHttpMediaTypeException(final HttpMediaTypeException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(UN_SUPPORTED_MEDIA_TYPE_EXCEPTION);
	}

	/**
	 * 502 Bad Gateway
	 */
	//TODO: Slack Or Discord 알림 처리
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(BadGatewayException.class)
	protected ApiResponse<Object> handleBadGatewayException(final BadGatewayException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(BAD_GATEWAY_ERROR);
	}

	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(WebClientException.class)
	protected ApiResponse<Object> handleWebClientException(final WebClientException exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(BAD_GATEWAY_ERROR);
	}

	/**
	 * 500 Internal Server Error
	 */
	//TODO: Slack Or Discord 알림 처리
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected ApiResponse<Object> handleException(final Exception exception) {
		log.error(exception.getMessage(), exception);
		return ApiResponse.error(INTERNAL_SERVER_ERROR);
	}
}
