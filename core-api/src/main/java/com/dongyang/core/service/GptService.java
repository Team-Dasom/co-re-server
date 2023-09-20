package com.dongyang.core.service;

import org.springframework.stereotype.Service;

import com.dongyang.core.common.dto.request.GptQuestionRequest;
import com.dongyang.core.common.dto.response.GptQuestionResponse;
import com.dongyang.core.external.gpt.GptApiCaller;
import com.dongyang.core.external.gpt.dto.GptQuestionResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GptService {

	private final GptApiCaller gptApiCaller;

	public GptQuestionResponse question(GptQuestionRequest request) {
		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request);
		System.out.println();
		return new GptQuestionResponse(gptQuestionResponseDto.choices().get(0).message().content().replace("\n", ", "));
	}
}
