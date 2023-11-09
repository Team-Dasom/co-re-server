package com.dongyang.core.domain.auth.service;


import static com.dongyang.core.global.common.constants.message.AuthErrorMessage.*;

import com.dongyang.core.global.response.ErrorCode;
import java.util.List;
import java.util.Objects;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongyang.core.domain.auth.service.dto.request.TokenRequest;
import com.dongyang.core.domain.auth.service.dto.response.TokenResponse;
import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.domain.member.service.MemberServiceUtils;
import com.dongyang.core.global.common.constants.auth.RedisKey;
import com.dongyang.core.global.common.exception.model.UnAuthorizedException;
import com.dongyang.core.global.common.utils.JwtUtils;
import com.dongyang.core.global.common.utils.MessageUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CreateTokenService {

	private final MemberRepository memberRepository;

	private final RedisTemplate redisTemplate;

	private final JwtUtils jwtUtils;

	public TokenResponse createTokenInfo(Long memberId) {
		List<String> tokens = jwtUtils.createTokenInfo(memberId);
		return TokenResponse.of(tokens.get(0), tokens.get(1));
	}

	public TokenResponse reissueToken(TokenRequest request) {
		Long memberId = jwtUtils.getMemberIdFromJwt(request.getAccessToken());
		Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
		if (!jwtUtils.validateToken(request.getRefreshToken())) {
			throw new UnAuthorizedException(
				MessageUtils.generate(INVALID_JWT_REFRESH_TOKEN_ERROR_MESSAGE, request.getRefreshToken()),
					ErrorCode.JWT_UNAUTHORIZED_ERROR);
		}
		String refreshToken = (String)redisTemplate.opsForValue().get(RedisKey.REFRESH_TOKEN + memberId);
		if (Objects.isNull(refreshToken)) {
			throw new UnAuthorizedException(
				MessageUtils.generate(EXPIRED_JWT_REFRESH_TOKEN_ERROR_MESSAGE, request.getRefreshToken()), ErrorCode.JWT_UNAUTHORIZED_ERROR);
		}
		if (!refreshToken.equals(request.getRefreshToken())) {
			jwtUtils.expireRefreshToken(member.getId());
			throw new UnAuthorizedException(
				MessageUtils.generate(WRONG_JWT_REFRESH_TOKEN_ERROR_MESSAGE, request.getRefreshToken()), ErrorCode.JWT_UNAUTHORIZED_ERROR);
		}
		List<String> tokens = jwtUtils.createTokenInfo(memberId);
		return TokenResponse.of(tokens.get(0), tokens.get(1));
	}
}
