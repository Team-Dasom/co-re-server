package com.dongyang.core.api.gpt;

import static com.dongyang.core.global.response.SuccessCode.ADD_COMMENT_SUCCESS;
import static com.dongyang.core.global.response.SuccessCode.RECOMMEND_VARIABLE_NAME_SUCCESS;
import static com.dongyang.core.global.response.SuccessCode.REFACTOR_CODE_SUCCESS;

import com.dongyang.core.api.gpt.dto.request.GptRequest;
import com.dongyang.core.api.gpt.dto.request.GptSolveAlgorithmRequest;
import com.dongyang.core.api.gpt.service.GptService;
import com.dongyang.core.api.gpt.dto.response.GptQuestionResponse;
import com.dongyang.core.global.common.interceptor.auth.Auth;
import com.dongyang.core.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Gpt")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GptController {
    private final GptService gptService;

    @Operation(summary = "변수명 추천")
    @PostMapping("/gpt/recommendVariableName")
    @ResponseStatus(HttpStatus.OK)
    @Auth
    public ApiResponse<GptQuestionResponse> recommendVariableName(@Valid @RequestBody GptRequest request) {
        return ApiResponse.success(RECOMMEND_VARIABLE_NAME_SUCCESS, gptService.recommendVariableName(request));
    }

    @Operation(summary = "설명 주석 추가")
    @PostMapping("/gpt/addComment")
    @ResponseStatus(HttpStatus.OK)
    @Auth
    public ApiResponse<GptQuestionResponse> addComment(@Valid @RequestBody GptRequest request) {
        return ApiResponse.success(ADD_COMMENT_SUCCESS, gptService.addComment(request));
    }

    @Operation(summary = "코드 리팩토링")
    @PostMapping("/gpt/refactorCode")
    @ResponseStatus(HttpStatus.OK)
    @Auth
    public ApiResponse<GptQuestionResponse> refactorCode(@Valid @RequestBody GptRequest request) {
        return ApiResponse.success(REFACTOR_CODE_SUCCESS, gptService.refactorCode(request));
    }

    @Operation(summary = "알고리즘 문제 해설[BETA]")
    @PostMapping("/gpt/solveAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    @Auth
    public ApiResponse<GptQuestionResponse> solveAlgorithm(@Valid @RequestBody GptSolveAlgorithmRequest request) {
        return ApiResponse.success(REFACTOR_CODE_SUCCESS, gptService.solveAlgorithm(request));
    }
}
