package com.dongyang.core.service.gpt;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GptMessage {
    private String role;
    private String content;

    public static GptMessage of(String role, String content) {
        return GptMessage.builder()
                .role(role)
                .content(content)
                .build();
    }
}
