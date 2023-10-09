package com.dongyang.core.global.common.resolver;


import static com.dongyang.core.global.common.constants.message.AuthErrorMessage.*;

import java.util.Optional;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.dongyang.core.global.common.constants.auth.JwtKey;
import com.dongyang.core.global.common.exception.model.InternalServerException;
import com.dongyang.core.global.common.utils.MessageUtils;
import com.dongyang.core.global.common.interceptor.auth.Auth;

@Component
public class MemberIdResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(MemberId.class) && Long.class.equals(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		Optional<Auth> auth = Optional.ofNullable(parameter.getMethodAnnotation(Auth.class));
		auth.orElseThrow(() -> new InternalServerException(NEED_AUTH_ANNOTATION_ERROR_MESSAGE));

		Optional<Object> object = Optional.ofNullable(webRequest.getAttribute(JwtKey.MEMBER_ID, 0));
		return object.orElseThrow(() -> new InternalServerException(
			MessageUtils.generate(CAN_NOT_GET_MEMBER_ID_ERROR_MESSAGE, parameter.getClass(),
				parameter.getMethod())));

	}
}
