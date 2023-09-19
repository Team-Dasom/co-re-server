package com.dongyang.core.external.dto.gpt;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
	JSON 구조를 객체로 편리하게 매핑하기 위하여 record 사용
 */
public record GptQuestionResponseDto(
	String id,
	String object,
	long created,
	String model,
	Usage usage,
	List<Choices> choices
) {

	public record Choices(
		Message message,
		String finishReason,
		int index
	) {
		public record Message(
			String role,
			String content
		) {
		}
	}

	public record Usage(
		int promptTokens,
		int completionTokens,
		int totalTokens
	) {
	}

}
