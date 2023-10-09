package com.dongyang.core.domain.member;


import com.dongyang.core.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "MEMBER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "NICKNAME", nullable = false, length = 30)
	private String nickname;

	@Column(name = "PROFILE_IMAGE_URL", nullable = false, length = 300)
	private String profileImageUrl;

	@Column(name = "CAREER")
	private int career;

	@Column(name = "MEMBER_ROLE", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private MemberRole role;

	@Embedded
	private SocialInfo socialInfo;

	public static Member newInstance(String socialId, MemberSocialType socialType, String nickname, String profileImageUrl) {
		return Member.builder()
			.socialInfo(SocialInfo.of(socialId, socialType))
			.nickname(nickname)
			.profileImageUrl(profileImageUrl)
			.role(MemberRole.MEMBER)
			.build();
	}
}
