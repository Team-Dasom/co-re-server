package com.dongyang.core.api.auth.dto.request;

import com.dongyang.core.domain.member.MemberSocialType;
import com.dongyang.core.api.member.dto.CreateMemberRequest;
import com.dongyang.core.external.client.auth.github.dto.response.GithubProfileResponse;
import com.dongyang.core.external.client.auth.kakao.dto.response.KakaoProfileResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

	@Schema(description = "소셜 로그인 타입", example = "KAKAO")
	@NotNull(message = "{auth.socialType.notNull}")
	private MemberSocialType socialType;

	@Schema(description = "소셜 토큰", example = "eyJhbGciOiJIUzUxdfadfadsMiJ9.udnKnDSK08EuX56E5k-")
	@NotBlank(message = "{auth.token.notBlank}")
	private String token;


	public CreateMemberRequest toCreateMemberDto(KakaoProfileResponse response) {
		return CreateMemberRequest.of(response.getId(), socialType, response.getNickname(), response.getThumbnailImage());
	}

	public CreateMemberRequest toCreateMemberDto(GithubProfileResponse response) {
		return CreateMemberRequest.of(response.getId(), socialType, response.getLogin(), response.getAvatarUrl());
	}
}
