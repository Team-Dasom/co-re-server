package com.dongyang.core.service.gpt;

import com.dongyang.core.controller.dto.request.GptRequestDto;
import com.dongyang.core.controller.exception.model.OpenAIException;

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
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GptService {
    @Value("${gpt.apikey}")
    private String API_KEY;

    @Value("${gpt.url}")
    private String API_URL;

    private final RestTemplate restTemplate;

    private final static String GPT_JSON_CHOICES_HEADER = "choices";
    private final static int GPT_JSON_INDEX_OF_MESSAGE_HEADER = 0;
    private final static String GPT_JSON_MESSAGE_HEADER = "message";
    private final static String GPT_JSON_CONTENT_HEADER = "content";


    public String sendRequest(GptRequestDto requestDto) {
        try {
            return parseGPTResponseContent(requestToGPT(API_URL, createRequest(requestDto)));

        } catch (RestClientException | ParseException e) {
            throw new OpenAIException("OpenAI API 호출 중 오류가 발생하였습니다.", e);
        }
    }

    private ResponseEntity<String> requestToGPT(String url, HttpEntity<Map<String, Object>> request) {
        // postForEntity
        // url: 요청 URL, request: requestData, String.class: 응답내용의 형태
        return restTemplate.postForEntity(url, request, String.class);
    }

    private HttpEntity<Map<String, Object>> createRequest(GptRequestDto requestDto) {
        // 질문에 대한 requestData 생성
        return new HttpEntity<>(
                createRequestBody(createChatMessages(requestDto)),
                createRequestHeader());
    }

    private List<GptMessage> createChatMessages(GptRequestDto requestDto) {
        List<GptMessage> gptMessages = new ArrayList<>();
        gptMessages.add(GptMessage.of("user", requestDto.getQuestion()));

        return gptMessages;
    }

    private HttpHeaders createRequestHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);
        return headers;
    }


    private String parseGPTResponseContent(ResponseEntity<String> response) throws ParseException {
        String body = response.getBody();

        JSONParser jsonParser = new JSONParser();
        JSONObject bodyJson = (JSONObject) jsonParser.parse(body);

        JSONArray choicesJson = (JSONArray) bodyJson.get(GPT_JSON_CHOICES_HEADER);

        JSONObject choiceJson = (JSONObject) choicesJson.get(GPT_JSON_INDEX_OF_MESSAGE_HEADER);
        JSONObject messageJson = (JSONObject) choiceJson.get(GPT_JSON_MESSAGE_HEADER);

        return messageJson.get(GPT_JSON_CONTENT_HEADER).toString();
    }

    private Map<String, Object> createRequestBody(List<GptMessage> gptMessages) {
        Map<String, Object> requestBody = new HashMap<>();

        // 권한, 요청내용 담기
        requestBody.put("messages", gptMessages);

        // 요청에 사용될 모델 설정
        requestBody.put("model", "gpt-3.5-turbo");

        // 완료시 생성할 최대 토큰수
        requestBody.put("max_tokens", 100);
        return requestBody;
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
}