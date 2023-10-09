package com.dongyang.core.domain.member.repository;


import static com.dongyang.core.domain.member.QMember.*;

import java.util.Optional;

import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.MemberSocialType;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public boolean existsBySocialIdAndSocialType(String socialId, MemberSocialType socialType) {
		return Optional.ofNullable(queryFactory.selectOne()
			.from(member)
			.where(
				member.socialInfo.socialId.eq(socialId),
				member.socialInfo.socialType.eq(socialType)
			)
			.fetchFirst()).isPresent();
	}

	@Override
	public Optional<Member> findMemberById(Long id) {
		return Optional.ofNullable(queryFactory
			.selectFrom(member)
			.where(member.id.eq(id))
			.fetchOne());
	}

	@Override
	public Optional<Member> findMemberBySocialIdAndSocialType(String socialId, MemberSocialType socialType) {
		return Optional.ofNullable(queryFactory
			.selectFrom(member)
			.where(
				member.socialInfo.socialId.eq(socialId),
				member.socialInfo.socialType.eq(socialType)
			)
			.fetchOne());
	}
}
