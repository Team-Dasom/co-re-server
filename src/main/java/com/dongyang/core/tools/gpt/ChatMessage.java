package com.dongyang.core.tools.gpt;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatMessage {
    private String role;
    private String content;

    public static ChatMessage of(String role, String content) {
        return ChatMessage.builder()
                .role(role)
                .content(content)
                .build();
    }
}
