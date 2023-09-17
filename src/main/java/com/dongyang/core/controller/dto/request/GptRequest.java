package com.dongyang.core.controller.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GptRequest {
    private String role;
    private String content;

    public static GptRequest of(String role, String content) {
        return GptRequest.builder()
                .role(role)
                .content(content)
                .build();
    }
}
