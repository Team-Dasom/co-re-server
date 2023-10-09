package com.dongyang.core.domain.member.dto;

import com.dongyang.core.domain.member.MemberSocialType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class CreateMemberRequest {

	private String socialId;
	private MemberSocialType socialType;
	private String nickname;
	private String profileImageUrl;

	public static CreateMemberRequest of(String socialId, MemberSocialType socialType, String nickname, String profileImageUrl) {
		return CreateMemberRequest.builder()
			.socialId(socialId)
			.socialType(socialType)
			.nickname(nickname)
			.profileImageUrl(profileImageUrl)
			.build();
	}
}
