package com.dongyang.core.domain.gpt.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GptFunction {
	RECOMMEND_VARIABLE_NAME("당신은 %s 변수명 추천 전문가이다. 단어를 입력받으면 그 단어와 정말 어울리는 변수명을 10개 추천해줘", 0.7, 100),
	ADD_COMMENT("당신은 프로그래밍 언어와 그 언어로 작성된 코드를 받으면 이를 분석하는 코드 전문가이다. 값을 입력받으면 각각의 코드가 어떤 동작을 하는지 간략하게 설명한 주석을 각 코드 라인 별로 {주석}\\n{코드}' 형태로 달아서 리턴해줘", 0.1, 2000),
	;

	private final String systemRoleMessage;
	private final double temperature;
	private final int maxToken;
}
