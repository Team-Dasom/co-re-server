package com.dongyang.core.domain.gpt.service;

import static com.dongyang.core.domain.gpt.constant.GptConstant.GPT_REQUEST_VALUE_ERROR_SIGN;
import static com.dongyang.core.domain.gpt.constant.GptConstant.MESSAGE_SYSTEM;
import static com.dongyang.core.domain.gpt.constant.GptConstant.MESSAGE_USER;
import static com.dongyang.core.global.common.constants.message.GptErrorMessage.GPT_REQUEST_VALUE_ERROR_MESSAGE;

import com.dongyang.core.external.gpt.GptApiCaller;
import com.dongyang.core.external.gpt.dto.gpt.GptMessage;
import com.dongyang.core.external.gpt.dto.gpt.GptQuestionResponse;
import com.dongyang.core.external.gpt.dto.gpt.GptQuestionResponseDto;
import com.dongyang.core.external.gpt.dto.gpt.GptRequest;
import com.dongyang.core.global.common.exception.model.GptRequestValueException;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GptService {

	private final GptApiCaller gptApiCaller;


	public GptQuestionResponse recommendVariableName(GptRequest request) {
		List<GptMessage> messages = generateMessages(
			String.format(request.getFunction().getSystemRoleMessage(), request.getLanguage(), request.getLanguage()), request.getContent());

		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request, messages);
		return new GptQuestionResponse(getContent(gptQuestionResponseDto));
	}

	public GptQuestionResponse addComment(GptRequest request) {
		List<GptMessage> messages = generateMessages(String.format(request.getFunction().getSystemRoleMessage(), request.getLanguage(), request.getLanguage(), request.getLanguage()),
			request.formatAddCommentRequest());

		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request, messages);
		return new GptQuestionResponse(getContent(gptQuestionResponseDto));
	}

	public GptQuestionResponse changeLanguage(GptRequest request) {
		List<GptMessage> messages = generateMessages(String.format(request.getFunction().getSystemRoleMessage()),
			request.formatChangeLanguageRequest());

		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request, messages);
		return new GptQuestionResponse(getContent(gptQuestionResponseDto));
	}

	public GptQuestionResponse refactorCode(GptRequest request) {
		List<GptMessage> messages = generateMessages(String.format(request.getLanguage(), request.getLanguage(), request.getLanguage(), request.getLanguage(), request.getLanguage()),
			request.formatCodeRefactorRequest());

		GptQuestionResponseDto gptQuestionResponseDto = gptApiCaller.sendRequest(request, messages);
		return new GptQuestionResponse(getContent(gptQuestionResponseDto));
	}


	private static List<GptMessage> generateMessages(String systemMessageText, String userMessageText) {
		GptMessage systemMessage = GptMessage.of(MESSAGE_SYSTEM, systemMessageText);
		GptMessage userMessage = GptMessage.of(MESSAGE_USER, userMessageText);

		return Arrays.asList(systemMessage, userMessage);
	}

	private String getContent(GptQuestionResponseDto gptQuestionResponseDto) {
		String response = gptQuestionResponseDto.choices().get(0).message().content();
		validationRequestValue(response);

		return response;
	}

	private void validationRequestValue(String gptQuestionResponse) {
		if(gptQuestionResponse.equals(GPT_REQUEST_VALUE_ERROR_SIGN)) {
			throw new GptRequestValueException(GPT_REQUEST_VALUE_ERROR_MESSAGE);
		}
	}
}
