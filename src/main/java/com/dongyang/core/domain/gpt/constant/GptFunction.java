package com.dongyang.core.domain.gpt.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GptFunction {
	RECOMMEND_VARIABLE_NAME("당신은 %s 변수명 추천 전문가이다. 단어를 입력받으면 그 단어와 정말 어울리는 변수명을 10개 추천해줘", 0.7, 2000),
	ADD_COMMENT("당신은 코드와 코드를 작성한 프로그래밍 언어를 입력받으면 코드를 분석한 후 주석을 달아주는 어시스트이다. 값을 입력받으면 주석으로 코드가 어떤 동작을 하는지에 대해서 '주석\\n코드' 형태로 간략한 설명을 달아서 리턴해줘", 0.2, 100),
	;

	private final String systemRoleMessage;
	private final double temperature;
	private final int maxToken;
}
