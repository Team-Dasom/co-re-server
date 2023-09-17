package com.dongyang.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dongyang.core.dto.ApiResponse;
import com.dongyang.core.dto.request.GptQuestionRequest;
import com.dongyang.core.dto.response.GptQuestionResponse;
import com.dongyang.core.service.GptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/gpt")
public class GptController {
	private final GptService gptService;

	@PostMapping("/question")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<GptQuestionResponse> questionToGpt(@RequestBody GptQuestionRequest request) {
		return ApiResponse.success(gptService.question(request));
	}

	// @GetMapping("/recommendVariableName")
	// @ResponseStatus(HttpStatus.OK)
	// public

}
