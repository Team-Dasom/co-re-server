package com.dongyang.core.domain.member.service;

import static com.dongyang.core.global.common.constants.message.MemberErrorMessage.*;

import java.util.Optional;

import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.MemberSocialType;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.global.common.exception.model.ConflictException;
import com.dongyang.core.global.common.exception.model.NotFoundException;
import com.dongyang.core.global.common.utils.MessageUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberServiceUtils {

	public static Member findMemberById(MemberRepository repository, Long memberId) {
		return repository.findById(memberId)
			.orElseThrow(() -> new NotFoundException(
				MessageUtils.generate(NOT_EXIST_MEMBER_SOCIAL_DATA_ERROR_MESSAGE, memberId)));
	}

	static void validateNotExistsMember(MemberRepository memberRepository, String socialId,
		MemberSocialType socialType) {
		if (memberRepository.existsBySocialIdAndSocialType(socialId, socialType)) {
			throw new ConflictException(
				MessageUtils.generate(ALREADY_EXIST_MEMBER_ERROR_MESSAGE, socialId, socialType));
		}
	}

	public static Member findMemberBySocialIdAndSocialType(MemberRepository memberRepository, String socialId,
		MemberSocialType socialType) {
		Optional<Member> member = memberRepository.findMemberBySocialIdAndSocialType(socialId, socialType);
		return member.orElseThrow(() -> new NotFoundException(
			MessageUtils.generate(NOT_EXIST_MEMBER_SOCIAL_DATA_ERROR_MESSAGE, socialType, socialId)));
	}
}
