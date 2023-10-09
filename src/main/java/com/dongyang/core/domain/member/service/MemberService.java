package com.dongyang.core.domain.member.service;

import org.springframework.stereotype.Service;

import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.dto.CreateMemberRequest;
import com.dongyang.core.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public Long registerMember(CreateMemberRequest request) {
		MemberServiceUtils.validateNotExistsMember(memberRepository, request.getSocialId(), request.getSocialType());
		Member member = memberRepository.save(
			Member.newInstance(request.getSocialId(), request.getSocialType(), request.getNickname(), request.getProfileImageUrl()));
		return member.getId();
	}
}
