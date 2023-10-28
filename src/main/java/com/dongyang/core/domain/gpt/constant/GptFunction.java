package com.dongyang.core.domain.gpt.constant;

import static com.dongyang.core.global.config.gpt.GptConfig.GPT_ADD_COMMENT_MODEL;
import static com.dongyang.core.global.config.gpt.GptConfig.GPT_DEFAULT_MODEL;
import static com.dongyang.core.global.config.gpt.GptConfig.GPT_RECOMMEND_VARIABLE_NAME_MODEL;
import static com.dongyang.core.global.config.gpt.GptConfig.GPT_REFACTOR_CODE_MODEL;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GptFunction {
    RECOMMEND_VARIABLE_NAME(
            "당신은 %s 언어 변수명 추천 전문가이다. 단어를 입력받으면 그 단어의 영어 변수명을 10개 생성하여 출력해라. 만약 %s 라는 언어가 존재하지 않는다면 X를 출력해라",
            GPT_RECOMMEND_VARIABLE_NAME_MODEL, 0.2, 100),
    ADD_COMMENT(
            "당신은 %s 언어로 작성된 코드를 받으면 이를 분석하여 설명 주석을 달아주는 코드 전문가이다. 만약 받은 코드가 %s 언어의 문법이 아니면 X를 출력하고, 받은 코드가 %s 언어로 작성된 것이 맞다면 각 코드 라인의 윗줄에 코드 기능 설명을 주석으로 달아라.",
            GPT_ADD_COMMENT_MODEL, 0.4, 2000),
    REFACTOR_CODE(
            "당신은 %s 언어로 작성된 코드를 입력받으면 클린코드로 리팩토링해주는 코드 전문가이다. 만약 받은 코드가 %s 언어의 문법이 아니거나 %s라는 언어가 존재하지 않으면 X를 출력하고, 받은 코드가 %s 언어로 작성된 것이 맞다면 코드를 리팩토링하고 리팩토링 내용 요약과 함께 출력해라.",
            GPT_REFACTOR_CODE_MODEL, 0.4, 2000),
    SOLVE_ALGORITHM("당신은 %s 플랫폼의 알고리즘 문제해결 전문가이다. 언어와 문제가 입력되면, 입력받은 언어로 문제를 해결하고 정답 코드와 함께 해설을 출력해라",
            GPT_DEFAULT_MODEL, 0.3, 2000);

    private final String systemRoleMessage;
    private final String model;
    private final double temperature;
    private final int maxToken;
}
