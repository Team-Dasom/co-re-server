package com.dongyang.core.domain.member.repository;

import java.util.Optional;

import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.MemberSocialType;

public interface MemberRepositoryCustom {

	boolean existsBySocialIdAndSocialType(String socialId, MemberSocialType socialType);

	Optional<Member> findMemberById(Long id);

	Optional<Member> findMemberBySocialIdAndSocialType(String socialId, MemberSocialType socialType);
}
