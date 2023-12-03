package com.dongyang.core.external.gpt;

import com.dongyang.core.external.gpt.constant.GptFunction;
import com.dongyang.core.external.gpt.vo.GptMessage;
import com.dongyang.core.external.gpt.vo.GptQuestionResponseDto;
import java.util.List;

public interface GptApiCaller {

	GptQuestionResponseDto sendRequest(GptFunction gptFunction, List<GptMessage> requestBody);
}
