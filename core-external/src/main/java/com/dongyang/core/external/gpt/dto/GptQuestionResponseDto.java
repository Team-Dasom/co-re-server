package com.dongyang.core.external.gpt.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
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
