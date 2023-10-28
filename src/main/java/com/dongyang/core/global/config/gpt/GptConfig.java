package com.dongyang.core.global.config.gpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GptConfig {

    public static String GPT_RECOMMEND_VARIABLE_NAME_MODEL;
    public static String GPT_ADD_COMMENT_MODEL;
    public static String GPT_REFACTOR_CODE_MODEL;
    public static final String GPT_DEFAULT_MODEL = "gpt-3.5-turbo";

    @Value("${gpt.model.recommend-variable-name}")
    public void setGptRecommendVariableNameModel(String value) {
        GPT_RECOMMEND_VARIABLE_NAME_MODEL = value;
    }

    @Value("${gpt.model.add-comment}")
    public void setGptAddCommentModel(String value) {
        GPT_ADD_COMMENT_MODEL = value;
    }

    @Value("${gpt.model.refactor-code}")
    public void setGptRefactorCodeModel(String value) {
        GPT_REFACTOR_CODE_MODEL = value;
    }
}
