package com.dongyang.core.controller;

import com.dongyang.core.controller.dto.request.GptRequestDto;
import com.dongyang.core.service.gpt.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/gpt")
public class GptController {
    private final GptService gptService;

    @PostMapping("/question")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity questionToGpt(@RequestBody GptRequestDto request) {
        return ResponseEntity.ok(gptService.sendRequest(request));
    }

}
