package com.dongyang.core.domain.gpt.service;

import static com.dongyang.core.domain.gpt.constant.GptConstant.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dongyang.core.external.gpt.GptApiCaller;
import com.dongyang.core.external.gpt.dto.gpt.GptMessage;
import com.dongyang.core.external.gpt.dto.gpt.GptQuestionResponse;
import com.dongyang.core.external.gpt.dto.gpt.GptQuestionResponseDto;
import com.dongyang.core.external.gpt.dto.gpt.GptRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GptService {

	private final GptApiCaller gptApiCaller;


	public GptQuestionResponse recommendVariableName(GptRequest request) {

		GptMessage systemMessage = GptMessage.of(MESSAGE_SYSTEM, String.format(request.getFunction().getSystemRoleMessage(), request.getLanguage()));
		GptMessage userMessage = GptMessage.of(MESSAGE_USER, request.getContent());
		List<GptMessage> messages = Arrays.asList(systemMessage, userMessage);

		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request, messages);
		return new GptQuestionResponse(getContent(gptQuestionResponseDto));
	}

	public GptQuestionResponse addComment(GptRequest request) {
		GptMessage systemMessage = GptMessage.of(MESSAGE_SYSTEM, String.format(request.getFunction().getSystemRoleMessage()));
		GptMessage userMessage = GptMessage.of(MESSAGE_USER, request.formatAddCommentRequest());
		List<GptMessage> messages = Arrays.asList(systemMessage, userMessage);

		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request, messages);
		return new GptQuestionResponse(getContent(gptQuestionResponseDto));
	}

	private String getContent(GptQuestionResponseDto gptQuestionResponseDto) {
		return gptQuestionResponseDto.choices().get(0).message().content();
	}
}
