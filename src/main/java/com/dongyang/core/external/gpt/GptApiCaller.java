package com.dongyang.core.external.gpt;

import com.dongyang.core.domain.gpt.constant.GptFunction;
import com.dongyang.core.external.gpt.dto.gpt.GptMessage;
import com.dongyang.core.external.gpt.dto.gpt.GptQuestionResponseDto;
import java.util.List;

public interface GptApiCaller {

	GptQuestionResponseDto sendRequest(GptFunction gptFunction, List<GptMessage> requestBody);
}
