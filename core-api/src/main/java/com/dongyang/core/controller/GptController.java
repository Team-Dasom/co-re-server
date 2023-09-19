package com.dongyang.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dongyang.core.dto.ApiResponse;
import com.dongyang.core.external.dto.gpt.GptQuestionResponse;
import com.dongyang.core.external.dto.gpt.GptRequest;
import com.dongyang.core.service.GptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/gpt")
public class GptController {
	private final GptService gptService;

	@PostMapping("/question")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GptQuestionResponse> questionToGpt(@RequestBody GptRequest request) {
		return ApiResponse.success(HttpStatus.OK, gptService.question(request));
	}

	@PostMapping("/recommendVariableName")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GptQuestionResponse> recommendVariable(@RequestBody GptRequest request) {
		return ApiResponse.success(HttpStatus.OK, gptService.recommendVariable(request));
	}

	@PostMapping("/addComment")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GptQuestionResponse> addComment(@RequestBody GptRequest request) {
		return ApiResponse.success(HttpStatus.OK, gptService.addComment(request));
	}

}
