package com.dongyang.core.domain.favorite.dto.request;

import com.dongyang.core.domain.gpt.constant.FunctionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ChangeFavoriteStateRequest {

    @Schema(description = "기능 타입", examples = {"ADD_COMMENT", "RECOMMEND_VARIABLE_NAME", "REFACTOR_CODE"})
    @NotNull(message = "{favorite.functionType.notNull}")
    private FunctionType functionType;

    @Schema(description = "요청 내용")
    @NotBlank(message = "{favorite.question.notBlank}")
    private String question;

    @Schema(description = "응답 내용")
    @NotBlank(message = "{favorite.answer.notBlank}")
    private String answer;

    @Schema(description = "기능 요청 시간")
    @NotNull(message = "{favorite.questionedAt.notNull}")
    private LocalDateTime questionedAt;
}
