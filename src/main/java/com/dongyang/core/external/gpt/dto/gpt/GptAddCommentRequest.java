package com.dongyang.core.external.gpt.dto.gpt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GptAddCommentRequest {
	private String code;
	private String language;

	public void formatAddCommentRequest() {
		code = code + "\n이 코드는 " + language + " 언어로 개발된 코드인데 주석달아서 설명해줘.";
	}

}
