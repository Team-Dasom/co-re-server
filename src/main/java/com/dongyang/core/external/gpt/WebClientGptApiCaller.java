package com.dongyang.core.external.gpt;

import static com.dongyang.core.constants.message.CommonErrorMessage.*;
import static com.dongyang.core.constants.message.GptErrorMessage.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.dongyang.core.dto.request.GptQuestionRequest;
import com.dongyang.core.exception.model.BadGatewayException;
import com.dongyang.core.exception.model.ValidationException;
import com.dongyang.core.external.gpt.dto.GptQuestionDto;
import com.dongyang.core.external.gpt.dto.GptQuestionResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
@Slf4j
@Primary
public class WebClientGptApiCaller implements GptApiCaller {

	private final WebClient webClient;
	private final ObjectMapper mapper;

	private final String GPT_MODEL_NAME = "gpt-3.5-turbo";
	private final String MODEL = "model";
	private final String MAX_TOKENS = "max_tokens";
	private final int MAX_TOKEN_VALUE = 100;
	private final String MESSAGES = "messages";

	@Value("${gpt.apikey}")
	private String API_KEY;

	@Value("${gpt.uri}")
	private String API_URI;

	@Override
	public GptQuestionResponseDto sendRequest(GptQuestionRequest request) {
		List<GptQuestionDto> question = Arrays.asList(GptQuestionDto.of("user", request.getQuestion()));

		return webClient.post()
			.uri(API_URI)
			.headers(headers -> {
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setBearerAuth(API_KEY);
			})
			.body(BodyInserters.fromValue(toJsonString(createRequestBody(question))))
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
				Mono.error(new ValidationException(
					WRONG_GPT_ACCESS_ERROR_MESSAGE)))
			.onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
				Mono.error(new BadGatewayException(GPT_INTERLOCK_ERROR_MESSAGE)))
			.bodyToMono(GptQuestionResponseDto.class)
			.block();
	}

	private Map<String, Object> createRequestBody(List<GptQuestionDto> gptRequests) {
		Map<String, Object> requestBody = new HashMap<>();

		// 권한, 요청내용 담기
		requestBody.put(MESSAGES, gptRequests);

		// 요청에 사용될 모델 설정
		requestBody.put(MODEL, GPT_MODEL_NAME);

		// 완료시 생성할 최대 토큰수
		requestBody.put(MAX_TOKENS, MAX_TOKEN_VALUE);
		return requestBody;
	}

	private String toJsonString(Map<String, Object> body) {
		try {
			log.info(mapper.writeValueAsString(body));
			return mapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			throw new com.dongyang.core.exception.model.JsonProcessingException(JSON_PROCESSING_ERROR_MESSAGE, e);
		}
	}
}
