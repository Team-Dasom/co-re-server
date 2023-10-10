package com.dongyang.core.external.client.auth.kakao;

import static com.dongyang.core.global.common.constants.message.AuthErrorMessage.*;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.dongyang.core.external.client.auth.kakao.dto.response.KakaoProfileResponse;
import com.dongyang.core.global.common.exception.model.BadGatewayException;
import com.dongyang.core.global.common.exception.model.ValidationException;
import com.dongyang.core.global.common.utils.MessageUtils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class WebClientKakaoCaller implements KakaoApiCaller {

	private final WebClient webClient;

	@Override
	public KakaoProfileResponse getProfileInfo(String accessToken) {
		return webClient.get()
			.uri("https://kapi.kakao.com/v2/user/me")
			.headers(headers -> headers.setBearerAuth(accessToken))
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
				Mono.error(new ValidationException(
					MessageUtils.generate(WRONG_OAUTH2_ACCESS_TOKEN_ERROR_MESSAGE, accessToken))))
			.onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
				Mono.error(new BadGatewayException(OAUTH2_LOGIN_ERROR_MESSAGE)))
			.bodyToMono(KakaoProfileResponse.class)
			.block();
	}

}
