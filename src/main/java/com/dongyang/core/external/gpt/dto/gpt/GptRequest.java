package com.dongyang.core.external.gpt.dto.gpt;

import com.dongyang.core.domain.gpt.GptFunction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GptRequest {
	private GptFunction function;
	private String content;
	private String language;

	public String formatAddCommentRequest() {
		return language + " 언어로 개발된 코드\n" + content;
	}
}
