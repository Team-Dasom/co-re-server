package com.dongyang.core.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GptResponseDto {

    private final String message;
}
