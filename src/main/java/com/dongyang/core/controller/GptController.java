package com.dongyang.core.controller;

import com.dongyang.core.controller.dto.ApiResponse;
import com.dongyang.core.external.gpt.GptApiCaller;
import com.dongyang.core.external.gpt.request.GptQuestionRequest;
import com.dongyang.core.external.gpt.response.GptQuestionResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/gpt")
public class GptController {
    private final GptApiCaller gptService;

    @PostMapping("/question")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<GptQuestionResponse> questionToGpt(@RequestBody GptQuestionRequest request) {
        return ApiResponse.success(gptService.sendRequest(request));
    }

    // @GetMapping("/recommendVariableName")
    // @ResponseStatus(HttpStatus.OK)
    // public

}
