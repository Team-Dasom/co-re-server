package com.dongyang.core.external.gpt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
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
