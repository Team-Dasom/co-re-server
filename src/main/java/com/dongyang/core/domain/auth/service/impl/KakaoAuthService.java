package com.dongyang.core.domain.auth.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongyang.core.domain.auth.service.AuthService;
import com.dongyang.core.domain.auth.service.dto.request.LoginRequest;
import com.dongyang.core.domain.auth.service.dto.request.SignUpRequest;
import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.MemberSocialType;
import com.dongyang.core.domain.member.dto.CreateMemberRequest;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.domain.member.service.MemberService;
import com.dongyang.core.domain.member.service.MemberServiceUtils;
import com.dongyang.core.external.client.auth.kakao.KakaoApiCaller;
import com.dongyang.core.external.client.auth.kakao.dto.response.KakaoProfileResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class KakaoAuthService implements AuthService {

	private final KakaoApiCaller kakaoApiCaller;

	private final MemberRepository memberRepository;

	private final MemberService memberService;

	@Override
	public Long signUp(SignUpRequest request) {
		KakaoProfileResponse response = kakaoApiCaller.getProfileInfo(request.getToken());
		return memberService.registerMember(request.toCreateMemberDto(response));
	}

	@Override
	public Long login(LoginRequest request) {
		KakaoProfileResponse response = kakaoApiCaller.getProfileInfo(request.getToken());
		Member member = MemberServiceUtils.findMemberBySocialIdAndSocialType(memberRepository, response.getId(),
			MemberSocialType.KAKAO);
		return member.getId();
	}
}
