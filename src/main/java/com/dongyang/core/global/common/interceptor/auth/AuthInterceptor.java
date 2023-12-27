package com.dongyang.core.global.common.interceptor.auth;

import com.dongyang.core.global.common.constants.auth.JwtKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

	private final LoginCheckHandler loginCheckHandler;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Optional<Auth> auth = Optional.ofNullable(handlerMethod.getMethodAnnotation(Auth.class));
		if (auth.isEmpty()) {
			return true;
		}
		Long memberId = loginCheckHandler.getMemberId(request);
		request.setAttribute(JwtKey.MEMBER_ID, memberId);
		return true;
	}
}
