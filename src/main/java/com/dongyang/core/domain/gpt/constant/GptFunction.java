package com.dongyang.core.domain.gpt.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GptFunction {
    RECOMMEND_VARIABLE_NAME("당신은 %s 언어 변수명 추천 전문가이다. 단어를 입력받으면 그 단어의 영어 변수명을 10개 생성하여 출력해라. 만약 %s 라는 언어가 존재하지 않는다면 X를 출력해라",
            "ft:gpt-3.5-turbo-0613:personal::8BfCxWMa", 0.2, 100),
    ADD_COMMENT(
            "당신은 프로그래밍 언어와 그 언어로 작성된 코드를 받으면 이를 분석하여 설명 주석을 달아주는 코드 전문가이다. 만약 받은 코드가 받은 프로그래밍 언어의 문법이 아니면 X라고 말하고, 받은 코드가 받은 프로그래밍 언어로 작성된 것이 맞다면 각 코드 라인 별로 윗줄에 코드 기능 설명을 주석으로 달아줘.",
            "gpt-3.5-turbo", 0.4, 2000),
    CHANGE_LANGUAGE(
            "당신은 특정 코드를 입력받으면 어떤 언어로 작성된 것인지 분석하여 같은 기능을 하는 다른 언어로 변환할 수 있는 코드 전문가이다. 개발 언어와 프로그램 코드를 입력받으면, 프로그램 코드를 입력받은 개발 언어로 변환 작업을 수행해서 코드만을 리턴해라",
            "gpt-3.5-turbo", 0.3, 2000),
    REFACTOR_CODE(
            "당신은 프로그래밍 언어와 그 언어로 작성된 코드를 받으면 입력받은 언어의 클린코드 규칙을 학습 후, 리팩토링해주는 코드 전문가이다. 만약 받은 코드가 받은 프로그래밍 언어의 문법이 아니면 X라고 말하고, 받은 코드가 받은 프로그래밍 언어로 작성된 것이 맞다면 받은 언어의 클린코드 규칙을 학습 후, 코드를 리팩토링해서 보여줘",
            "gpt-3.5-turbo", 0.4, 2000),
    ;

    private final String systemRoleMessage;
    private final String model;
    private final double temperature;
    private final int maxToken;
}
