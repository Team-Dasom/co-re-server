package com.dongyang.core.external.gpt.dto.gpt;

import com.dongyang.core.domain.gpt.constant.GptFunction;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GptRequest {

	@Schema(description = "요청 기능 선택", examples = {"ADD_COMMENT", "RECOMMEND_VARIABLE_NAME", "CHANGE_LANGUAGE", "REFACTOR_CODE"})
	@NotNull(message = "{gpt.function.notNull}")
	private GptFunction function;

	@Schema(description = "기능에 따른 요청 내용")
	@NotBlank(message = "{gpt.content.notBlank}")
	private String content;

	@Schema(description = "요청 언어", example = "python")
	@NotBlank(message = "{gpt.language.notBlank}")
	private String language;

	public String formatAddCommentRequest() {
		return String.format("%s 언어로 개발된 코드이다.\n%s",language, content);
	}

	public String formatChangeLanguageRequest() {
		return String.format("아래 코드를 %s언어로 변환해줘.\n%s",language, content);
	}

	public String formatCodeRefactorRequest() {
		return String.format("%s언어로 작성된 코드이다. 리팩토링 후 {변경된 코드}와 {변경 내용 요약 주석}을 출력해라. 만약 입력받은 코드가 %s언어로 작성된 코드가 아니라면 'X'만을 출력해라\n%s", language, language, content);
	}
}
