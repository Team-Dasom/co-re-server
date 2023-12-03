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
public class GptRequest {

    @Schema(description = "요청 기능 선택", examples = {"ADD_COMMENT", "RECOMMEND_VARIABLE_NAME", "REFACTOR_CODE"})
    @NotNull(message = "{gpt.function.notNull}")
    private GptFunction function;

    @Schema(description = "기능에 따른 요청 내용")
    @NotBlank(message = "{gpt.content.notBlank}")
    private String content;

    @Schema(description = "요청 언어", example = "python")
    @NotBlank(message = "{gpt.language.notBlank}")
    private String language;

}
