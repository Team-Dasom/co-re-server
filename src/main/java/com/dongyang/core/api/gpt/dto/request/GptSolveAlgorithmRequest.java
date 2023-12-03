package com.dongyang.core.api.gpt.dto.request;

import com.dongyang.core.external.gpt.constant.GptFunction;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GptSolveAlgorithmRequest {

    @Schema(description = "요청 기능 선택", example = "SOLVE_ALGORITHM")
    @NotNull(message = "{gpt.function.notNull}")
    private GptFunction function;

    @Schema(description = "알고리즘 문제 플랫폼")
    @NotBlank(message = "{gpt.content.notBlank}")
    private String platform;

    @Schema(description = "문제 정보")
    @NotBlank(message = "{gpt.content.notBlank}")
    private String problem;

    @Schema(description = "요청 언어", example = "python")
    @NotBlank(message = "{gpt.language.notBlank}")
    private String language;


    public String createUserMessageText() {
        return String.format("%s 언어로 %s 문제 풀고 설명해줘", language, problem);
    }
}
