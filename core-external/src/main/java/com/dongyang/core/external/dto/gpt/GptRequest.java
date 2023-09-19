package com.dongyang.core.external.dto.gpt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GptRequest {
	private String content;
	private String language;

	public void formatRequestRecommendVariableQuestion() {
		content = "'" + content + "' 라는 단어에 대해 " + language + "언어 기준으로 변수명 10개 추천해줘.";
	}

	public void formatAddCommentRequest() {
		content = content + "\n이 코드는 "+language+"로 작성한 코드이다. 어떤 기능을 하는 지에 대해 한글로 설명 주석을 달아서 코드만 보여줘";
	}
}
