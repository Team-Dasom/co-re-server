package com.dongyang.core.global.common.interceptor.admin;

import java.util.Optional;



import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AdminInterceptor implements HandlerInterceptor {

	private final AdminCheckHandler adminCheckHandler;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Optional<Admin> admin = Optional.ofNullable(handlerMethod.getMethodAnnotation(Admin.class));
		if (admin.isEmpty()) {
			return true;
		}
		adminCheckHandler.validateMemberRole(request);
		return true;
	}
}