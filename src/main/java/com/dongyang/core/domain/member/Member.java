package com.dongyang.core.domain.member;

import com.dongyang.core.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "MEMBER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "MEMBER_NAME", nullable = false, length = 30)
	private String name;

	@Column(name = "PROFILE_IMAGE_URL", nullable = false, length = 300)
	private String profileImageUrl;

	@Column(name = "CAREER")
	private int career;
}
