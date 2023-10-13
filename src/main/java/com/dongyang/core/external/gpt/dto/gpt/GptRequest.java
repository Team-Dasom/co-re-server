package com.dongyang.core.external.gpt.dto.gpt;

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
		content = content + "\n이 코드는 "+language+" 언어로 작성한 코드이다. 코드의 기능 대해 각 코드의 윗 줄에 간단히 설명 주석을 달아서 코드 블럭형태로 리턴해줘";
	}
}
