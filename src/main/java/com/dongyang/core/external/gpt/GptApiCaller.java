package com.dongyang.core.external.gpt;

import java.util.List;

import com.dongyang.core.external.gpt.dto.gpt.GptQuestionResponseDto;
import com.dongyang.core.external.gpt.dto.gpt.GptRequest;
import com.dongyang.core.external.gpt.dto.gpt.GptMessage;

public interface GptApiCaller {

	GptQuestionResponseDto sendRequest(GptRequest request, List<GptMessage> requestBody);
}
