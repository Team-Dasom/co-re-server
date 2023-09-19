package com.dongyang.core.service;

import org.springframework.stereotype.Service;

import com.dongyang.core.external.dto.gpt.GptQuestionResponse;
import com.dongyang.core.external.dto.gpt.GptQuestionResponseDto;
import com.dongyang.core.external.dto.gpt.GptRequest;
import com.dongyang.core.external.gpt.GptApiCaller;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GptService {

	private final GptApiCaller gptApiCaller;

	public GptQuestionResponse question(GptRequest request) {
		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request, 100);
		return new GptQuestionResponse(getContent(gptQuestionResponseDto));
	}

	public GptQuestionResponse recommendVariable(GptRequest request) {
		request.formatRequestRecommendVariableQuestion();
		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request, 100);
		return new GptQuestionResponse(getContent(gptQuestionResponseDto));
	}

	public GptQuestionResponse addComment(GptRequest request) {
		request.formatAddCommentRequest();
		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request, 4000);
		return new GptQuestionResponse(getContent(gptQuestionResponseDto));
	}

	private String getContent(GptQuestionResponseDto gptQuestionResponseDto) {
		return gptQuestionResponseDto.choices().get(0).message().content();
	}
}
