package com.dongyang.core.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.domain.member.service.MemberServiceUtils;
import com.dongyang.core.global.common.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CommonAuthService {

	private final MemberRepository memberRepository;

	private final JwtUtils jwtUtils;

	public void logout(Long memberId) {
		Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
		jwtUtils.expireRefreshToken(member.getId());
	}
}
