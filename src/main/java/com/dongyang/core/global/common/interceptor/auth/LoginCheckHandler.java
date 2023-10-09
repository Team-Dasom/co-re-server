package com.dongyang.core.global.common.interceptor.auth;


import static com.dongyang.core.global.common.constants.message.AuthErrorMessage.*;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dongyang.core.global.common.exception.model.UnAuthorizedException;
import com.dongyang.core.global.common.utils.JwtUtils;
import com.dongyang.core.global.common.utils.MessageUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginCheckHandler {

	private final JwtUtils jwtUtils;

	public Long getMemberId(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			String accessToken = bearerToken.substring("Bearer ".length());
			if (jwtUtils.validateToken(accessToken)) {
				Long memberId = jwtUtils.getMemberIdFromJwt(accessToken);
				if (memberId != null) {
					return memberId;
				}
			}
		}
		throw new UnAuthorizedException(MessageUtils.generate(WRONG_JWT_ERROR_MESSAGE, bearerToken));
	}
}
