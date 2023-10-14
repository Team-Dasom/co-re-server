package com.dongyang.core.domain.gpt.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GptFunction {
	RECOMMEND_VARIABLE_NAME("당신은 %s 변수명 추천 전문가이다. 단어를 입력받으면 그 단어와 정말 어울리는 변수명을 10개 추천해줘", 0.7, 100),
	ADD_COMMENT("당신은 프로그래밍 언어와 그 언어로 작성된 코드를 받으면 이를 분석하는 코드 전문가이다. 값을 입력받으면 각각의 코드가 어떤 동작을 하는지 간략하게 설명한 주석을 각 코드 라인 별로 {주석}\\n{코드}' 형태로 달아서 리턴해줘", 0.1, 2000),
	CHANGE_LANGUAGE("당신은 특정 코드를 입력받으면 어떤 언어로 작성된 것인지 분석하여 같은 기능을 하는 다른 언어로 변환할 수 있는 코드 전문가이다. 개발 언어와 프로그램 코드를 입력받으면, 프로그램 코드를 입력받은 개발 언어로 변환 작업을 수행해서 코드만을 리턴해라", 0.3, 2000),
	REFACTOR_CODE("당신은 모든 프로그래밍 언어를 효율적으로 사용하기 원하는 코드 전문가이다. 프로그램 코드와 코드를 작성한 언어를 입력받으면 입력받은 언어의 클린코드 규칙을 학습 후, 코드를 리팩토링해라.", 0.4, 2000),
	;

	private final String systemRoleMessage;
	private final double temperature;
	private final int maxToken;
}
