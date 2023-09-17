package com.dongyang.core.external.gpt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dongyang.core.external.gpt.request.GptQuestionRequest;
import com.dongyang.core.controller.exception.model.OpenAIException;
import com.dongyang.core.controller.dto.request.GptRequest;
import com.dongyang.core.external.gpt.response.GptQuestionResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RestTemplateGptApiCaller implements GptApiCaller{
    @Value("${gpt.apikey}")
    private String API_KEY;

    @Value("${gpt.url}")
    private String API_URL;

    private final RestTemplate restTemplate;

    private final static String GPT_JSON_CHOICES_HEADER = "choices";
    private final static int GPT_JSON_INDEX_OF_MESSAGE_HEADER = 0;
    private final static String GPT_JSON_MESSAGE_HEADER = "message";
    private final static String GPT_JSON_CONTENT_HEADER = "content";


    public GptQuestionResponse sendRequest(GptQuestionRequest requestDto) {
        try {
            return new GptQuestionResponse(parseGPTResponseContent(requestToGPT(API_URL, createRequest(requestDto))));

        } catch (RestClientException | ParseException e) {
            throw new OpenAIException("OpenAI API 호출 중 오류가 발생하였습니다.", e);
        }
    }

    private ResponseEntity<String> requestToGPT(String url, HttpEntity<Map<String, Object>> request) {
        // postForEntity
        // url: 요청 URL, request: requestData, String.class: 응답내용의 형태
        return restTemplate.postForEntity(url, request, String.class);
    }

    private HttpEntity<Map<String, Object>> createRequest(GptQuestionRequest requestDto) {
        // 질문에 대한 requestData 생성
        return new HttpEntity<>(
                createRequestBody(createChatMessages(requestDto)),
                createRequestHeader());
    }

    private List<GptRequest> createChatMessages(GptQuestionRequest requestDto) {
        List<GptRequest> gptRequests = new ArrayList<>();
        gptRequests.add(GptRequest.of("user", requestDto.getQuestion()));

        return gptRequests;
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
        log.info(messageJson.toJSONString());
        log.info("GPT request Response : {}", messageJson.get(GPT_JSON_CONTENT_HEADER).toString());
        return messageJson.get(GPT_JSON_CONTENT_HEADER).toString();
    }

    private Map<String, Object> createRequestBody(List<GptRequest> gptRequests) {
        Map<String, Object> requestBody = new HashMap<>();

        // 권한, 요청내용 담기
        requestBody.put("messages", gptRequests);

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