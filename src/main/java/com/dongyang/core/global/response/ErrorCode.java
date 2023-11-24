package com.dongyang.core.global.response;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
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
    GPT_REQUEST_VALUE_EXCEPTION(BAD_REQUEST, "요청 값의 function, content, language가 매칭되지 않거나 문제가 있습니다."),
    INVALID_OAUTH2_TOKEN_ERROR(BAD_REQUEST, "잘못된 OAuth2 액세스 토큰 입니다"),
    INVALID_GPT_API_INFO_ERROR(BAD_REQUEST, "잘못된 GPT API 요청입니다. API키 또는 요청 방식을 재확인 하세요."),
    NOT_EXIST_MEMBER_ROLE(BAD_REQUEST, "존재하지 않는 회원 권한입니다."),

    /*
        401 - UNAUTHORIZED
     */
    JWT_UNAUTHORIZED_ERROR(UNAUTHORIZED, "JWT 토큰이 존재하지 않거나 유효하지 않습니다."),
    ADMIN_UNAUTHORIZED_ERROR(UNAUTHORIZED, "관리자 권한이 없습니다."),

    /*
        403 - FORBIDDEN
     */
    FORBIDDEN_EXCEPTION(FORBIDDEN, "해당 요청에 대한 요청 권한이 존재하지 않습니다."),

    /*
        404 - NOT FOUND
     */
    NOT_FOUND_EXCEPTION(NOT_FOUND, "존재하지 않는 요청입니다."),
    NOT_FOUND_MEMBER_EXCEPTION(NOT_FOUND, "존재하지 않거나 탈퇴한 유저입니다."),
    NOT_FOUND_FAVORITE_ERROR(NOT_FOUND, "존재하지 않는 즐겨찾기 데이터입니다."),

    /*
        405 - METHOD NOT ALLOWED
     */
    METHOD_NOT_ALLOWED_EXCEPTION(METHOD_NOT_ALLOWED, "잘못된 HTTP Method 요청입니다."),

    /*
        409 - CONFLICT
    */
    CONFLICT_EXCEPTION(CONFLICT, "데이터를 처리하는 과정에서 충돌이 발생하였습니다."),
    CONFLICT_MEMBER_EXCEPTION(CONFLICT, "이미 존재하는 회원입니다."),

    /*
        415 - UN SUPPORTED MEDIA TYPE
    */
    UN_SUPPORTED_MEDIA_TYPE_EXCEPTION(UNSUPPORTED_MEDIA_TYPE, "지원하지 않는 미디어 타입입니다."),

    /*
        429 - TOO MANY REQUESTS
    */
    TOKEN_NOT_EXIST_EXCEPTION(TOO_MANY_REQUESTS, "잔여 요청 토큰이 존재하지 않습니다. 잠시후 다시 시도해주세요."),

    /*
        500 - INTERNAL SERVER ERROR
    */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다."),

    /*
        502- BAD GATEWAY
     */
    BAD_GATEWAY_ERROR(BAD_GATEWAY, "외부 연동 중 에러가 발생하였습니다."),
    OAUTH2_BAD_GATEWAY_ERROR(BAD_GATEWAY, "OAuth2 소셜 로그인 연동 중 에러가 발생하였습니다."),
    GPT_BAD_GATEWAY_ERROR(BAD_GATEWAY, "GPT API 연동 중 에러가 발생하였습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
