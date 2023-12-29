package com.dongyang.core.api.auth.service.impl;

import com.dongyang.core.api.auth.dto.response.TokenResponse;
import com.dongyang.core.api.auth.service.CreateTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongyang.core.api.auth.service.AuthService;
import com.dongyang.core.api.auth.dto.request.LoginRequest;
import com.dongyang.core.api.auth.dto.request.SignUpRequest;
import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.MemberSocialType;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.api.member.service.MemberService;
import com.dongyang.core.api.member.service.MemberServiceUtils;
import com.dongyang.core.external.client.auth.github.GithubApiCaller;
import com.dongyang.core.external.client.auth.github.dto.response.GithubProfileResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class GithubAuthService implements AuthService {

	private final GithubApiCaller githubApiCaller;

	private final MemberRepository memberRepository;

	private final MemberService memberService;
	private final CreateTokenService createTokenService;

	@Override
	public TokenResponse signUp(SignUpRequest request) {
		GithubProfileResponse response = githubApiCaller.getProfileInfo(request.getToken());
		Long memberId = memberService.registerMember(request.toCreateMemberDto(response));

		return createTokenService.createTokenInfo(memberId);
	}

	@Override
	public TokenResponse login(LoginRequest request) {
		GithubProfileResponse response = githubApiCaller.getProfileInfo(request.getToken());
		Member member = MemberServiceUtils.findMemberBySocialIdAndSocialType(memberRepository, response.getId(),
			MemberSocialType.GITHUB);

		return createTokenService.createTokenInfo(member.getId());
	}
}
