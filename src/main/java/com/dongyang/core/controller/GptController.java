package com.dongyang.core.controller;

import com.dongyang.core.controller.dto.request.GptRequestDto;
import com.dongyang.core.utils.gpt.GptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gpt")
public class GptController {
    private final GptUtil gptUtil;

    @PostMapping("/question")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity questionToGpt(@RequestBody GptRequestDto request) {

        return ResponseEntity.ok(gptUtil.sendRequest(request));
    }

}
