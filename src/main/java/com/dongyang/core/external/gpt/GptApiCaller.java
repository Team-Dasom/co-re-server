package com.dongyang.core.external.gpt;

import com.dongyang.core.external.gpt.request.GptQuestionRequest;
import com.dongyang.core.external.gpt.response.GptQuestionResponse;

public interface GptApiCaller {

	GptQuestionResponse sendRequest(GptQuestionRequest request);
}
