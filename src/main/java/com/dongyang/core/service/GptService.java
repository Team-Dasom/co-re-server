package com.dongyang.core.service;

import com.dongyang.core.controller.dto.request.GptRequestDto;
import com.dongyang.core.tools.gpt.ChatMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GptService {
    @Value("${gpt.apikey}")
    private String API_KEY;

    private final RestTemplate restTemplate = restTemplate();

    public String requestToGpt(GptRequestDto requestDto) {
        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        // 요청 질문
        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(ChatMessage.of("user", requestDto.getQuestion()));
        requestBody.put("messages", chatMessages);

        // 요청에 사용될 모델 설정
        requestBody.put("model", "gpt-3.5-turbo");

        // 완료시 생성할 최대 토큰수
        requestBody.put("max_tokens", 1500);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            String body = response.getBody();

            JSONParser jsonParser = new JSONParser();
            JSONObject bodyJson = (JSONObject) jsonParser.parse(body);

            JSONArray choicesJson = (JSONArray) bodyJson.get("choices");

            JSONObject choiceJson = (JSONObject) choicesJson.get(0);
            JSONObject messageJson = (JSONObject) choiceJson.get("message");

            return messageJson.get("content").toString();

        } catch (RestClientException | ParseException e) {
            throw new OpenAIException("OpenAI API 호출 중 오류가 발생하였습니다.", e);
        }
    }


    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add((request, body, execution) -> {
            request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            request.getHeaders().setBearerAuth(API_KEY);
            return execution.execute(request, body);
        });
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

    public class OpenAIException extends RestClientException {
        public OpenAIException(String message) {
            super(message);
        }

        public OpenAIException(String message, Throwable cause) {
            super(message, cause);
        }

    }
}