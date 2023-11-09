package com.dongyang.core.external.client.auth.github;

import static com.dongyang.core.global.common.constants.message.AuthErrorMessage.*;

import com.dongyang.core.global.response.ErrorCode;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.dongyang.core.external.client.auth.github.dto.response.GithubProfileResponse;
import com.dongyang.core.global.common.exception.model.BadGatewayException;
import com.dongyang.core.global.common.exception.model.ValidationException;
import com.dongyang.core.global.common.utils.MessageUtils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class WebClientGithubCaller implements GithubApiCaller {

	private final WebClient webClient;

	@Override
	public GithubProfileResponse getProfileInfo(String accessToken) {
		return webClient.get()
			.uri("https://api.github.com/user")
			.headers(headers -> headers.setBearerAuth(accessToken))
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
				Mono.error(new ValidationException(
					MessageUtils.generate(WRONG_OAUTH2_ACCESS_TOKEN_ERROR_MESSAGE, accessToken),
						ErrorCode.INVALID_OAUTH2_TOKEN_ERROR)))
			.onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
				Mono.error(new BadGatewayException(OAUTH2_LOGIN_ERROR_MESSAGE, ErrorCode.OAUTH2_BAD_GATEWAY_ERROR)))
			.bodyToMono(GithubProfileResponse.class)
			.block();
	}
}
