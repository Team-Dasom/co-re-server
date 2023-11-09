package com.dongyang.core.external.gpt;

import static com.dongyang.core.domain.gpt.constant.GptConstant.MAX_TOKENS;
import static com.dongyang.core.domain.gpt.constant.GptConstant.MESSAGES;
import static com.dongyang.core.domain.gpt.constant.GptConstant.MODEL;
import static com.dongyang.core.domain.gpt.constant.GptConstant.TEMPERATURE;
import static com.dongyang.core.global.common.constants.message.GptErrorMessage.GPT_INTERLOCK_ERROR_MESSAGE;
import static com.dongyang.core.global.common.constants.message.GptErrorMessage.WRONG_GPT_ACCESS_ERROR_MESSAGE;
import static com.dongyang.core.global.common.constants.message.WebClientErrorMessage.WEB_CLIENT_CONNECTION_ERROR_MESSAGE;
import static com.dongyang.core.global.response.ErrorCode.WEB_CLIENT_BAD_GATEWAY_ERROR;
import static com.dongyang.core.global.response.ErrorCode.INVALID_GPT_API_INFO_ERROR;

import com.dongyang.core.domain.gpt.constant.GptFunction;
import com.dongyang.core.external.gpt.dto.gpt.GptMessage;
import com.dongyang.core.external.gpt.dto.gpt.GptQuestionResponseDto;
import com.dongyang.core.global.common.exception.model.BadGatewayException;
import com.dongyang.core.global.common.exception.model.ValidationException;
import com.dongyang.core.global.common.exception.model.WebClientException;
import com.dongyang.core.global.response.ErrorCode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
@Slf4j
public class WebClientGptApiCaller implements GptApiCaller {

    private final WebClient webClient;

    @Value("${gpt.apikey}")
    private String API_KEY;

    @Value("${gpt.uri}")
    private String API_URI;

    @Override
    public GptQuestionResponseDto sendRequest(GptFunction gptFunction, List<GptMessage> gptMessages) {

        return webClient.post()
                .uri(API_URI)
                .headers(headers -> {
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.setBearerAuth(API_KEY);
                })
                .bodyValue(createRequestBody(gptMessages, gptFunction))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new ValidationException(WRONG_GPT_ACCESS_ERROR_MESSAGE, INVALID_GPT_API_INFO_ERROR)))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(
                                new BadGatewayException(GPT_INTERLOCK_ERROR_MESSAGE, ErrorCode.GPT_BAD_GATEWAY_ERROR)))
                .bodyToMono(GptQuestionResponseDto.class)
                .doOnError(error -> {
                    throw new WebClientException(WEB_CLIENT_CONNECTION_ERROR_MESSAGE,
                            ErrorCode.WEB_CLIENT_BAD_GATEWAY_ERROR, error);
                })
                .block();
    }

    private Map<String, Object> createRequestBody(List<GptMessage> gptMessages, GptFunction gptFunction) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put(MESSAGES, gptMessages);
        requestBody.put(MODEL, gptFunction.getModel());
        requestBody.put(MAX_TOKENS, gptFunction.getMaxToken());
        requestBody.put(TEMPERATURE, gptFunction.getTemperature());

        return requestBody;
    }
}
