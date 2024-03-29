package com.dongyang.core.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MemberSocialType {
	KAKAO("카카오톡"),
	GITHUB("깃허브"),
	;
	private final String value;
}
