package com.dongyang.core.global.common.interceptor.admin;

import static com.dongyang.core.global.common.constants.message.AuthErrorMessage.*;

import com.dongyang.core.global.response.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.domain.member.MemberRole;
import com.dongyang.core.domain.member.service.MemberServiceUtils;
import com.dongyang.core.global.common.exception.model.ForbiddenException;
import com.dongyang.core.global.common.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AdminCheckHandler {

	private final JwtUtils jwtUtils;
	private final MemberRepository memberRepository;

	public void validateMemberRole(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			String accessToken = bearerToken.substring("Bearer ".length());
			if (hasAdminAuthority(accessToken)) {
				return;
			}
		}
		throw new ForbiddenException(ADMIN_ERROR_MESSAGE, ErrorCode.ADMIN_UNAUTHORIZED_ERROR);
	}

	private boolean hasAdminAuthority(String accessToken) {
		if (jwtUtils.validateToken(accessToken)) {
			Long memberId = jwtUtils.getMemberIdFromJwt(accessToken);
			if (memberId != null) {
				Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
				return member.getRole().equals(MemberRole.ADMIN);
			}
		}
		return false;
	}
}
