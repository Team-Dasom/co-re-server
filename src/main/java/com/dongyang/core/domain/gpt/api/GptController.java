package com.dongyang.core.domain.gpt.api;

import static com.dongyang.core.global.response.SuccessCode.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dongyang.core.domain.gpt.service.GptService;
import com.dongyang.core.external.gpt.dto.gpt.GptQuestionResponse;
import com.dongyang.core.external.gpt.dto.gpt.GptRequest;
import com.dongyang.core.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Gpt")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/gpt")
public class GptController {
	private final GptService gptService;

	@Operation(summary = "변수명 추천")
	@PostMapping("/recommendVariableName")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GptQuestionResponse> recommendVariableName(@RequestBody GptRequest request) {
		return ApiResponse.success(RECOMMEND_VARIABLE_NAME_SUCCESS, gptService.recommendVariableName(request));
	}

	@Operation(summary = "설명 주석 추가")
	@PostMapping("/addComment")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GptQuestionResponse> addComment(@RequestBody GptRequest request) {
		return ApiResponse.success(ADD_COMMENT_SUCCESS, gptService.addComment(request));
	}

}
