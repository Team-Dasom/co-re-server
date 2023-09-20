package com.dongyang.core.external.gpt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class GptQuestionDto {
	private String role;
	private String content;

	public static GptQuestionDto of(String role, String content) {
		return GptQuestionDto.builder()
			.role(role)
			.content(content)
			.build();
	}
}