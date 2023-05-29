package com.dongyang.core.controller;

import com.dongyang.core.controller.dto.request.GptRequestDto;
import com.dongyang.core.utils.gpt.GptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gpt")
public class GptController {
    private final GptUtil gptUtil;

    @GetMapping("/question")
    public ResponseEntity questionToGpt(@RequestBody GptRequestDto request) {

        return ResponseEntity.ok(gptUtil.sendRequest(request));
    }

}
