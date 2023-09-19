package com.dongyang.core.external.gpt;

import com.dongyang.core.external.dto.gpt.GptQuestionResponseDto;
import com.dongyang.core.external.dto.gpt.GptRequest;

public interface GptApiCaller {

	GptQuestionResponseDto sendRequest(GptRequest request, int maxTokenValue);
}
