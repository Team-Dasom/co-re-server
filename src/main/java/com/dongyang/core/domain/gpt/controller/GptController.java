package com.dongyang.core.domain.gpt.controller;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gpt")
public class GptController {
	private final GptService gptService;

	@PostMapping("/recommendVariableName")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GptQuestionResponse> recommendVariableName(@RequestBody GptRequest request) {
		return ApiResponse.success(RECOMMEND_VARIABLE_NAME_SUCCESS, gptService.recommendVariable(request));
	}

	@PostMapping("/addComment")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GptQuestionResponse> addComment(@RequestBody GptRequest request) {
		return ApiResponse.success(ADD_COMMENT_SUCCESS, gptService.addComment(request));
	}

}
