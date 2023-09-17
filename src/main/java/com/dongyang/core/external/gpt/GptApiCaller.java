package com.dongyang.core.external.gpt;

import com.dongyang.core.dto.request.GptQuestionRequest;
import com.dongyang.core.external.gpt.dto.GptQuestionResponseDto;

public interface GptApiCaller {

	GptQuestionResponseDto sendRequest(GptQuestionRequest request);
}
