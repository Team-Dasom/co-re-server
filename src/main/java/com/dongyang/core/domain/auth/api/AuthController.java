package com.dongyang.core.domain.auth.api;

import static com.dongyang.core.global.response.SuccessCode.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongyang.core.domain.auth.service.AuthService;
import com.dongyang.core.domain.auth.service.AuthServiceProvider;
import com.dongyang.core.domain.auth.service.CommonAuthService;
import com.dongyang.core.domain.auth.service.CreateTokenService;
import com.dongyang.core.domain.auth.service.dto.request.LoginRequest;
import com.dongyang.core.domain.auth.service.dto.request.SignUpRequest;
import com.dongyang.core.domain.auth.service.dto.request.TokenRequest;
import com.dongyang.core.domain.auth.service.dto.response.TokenResponse;
import com.dongyang.core.global.common.interceptor.auth.Auth;
import com.dongyang.core.global.common.resolver.MemberId;
import com.dongyang.core.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

	private final AuthServiceProvider authServiceProvider;
	private final CreateTokenService createTokenService;
	private final CommonAuthService commonAuthService;

	@Operation(summary = "카카오 소셜 회원가입")
	@PostMapping("/auth/signup")
	public ApiResponse<TokenResponse> signUp(@Valid @RequestBody SignUpRequest request) {
		AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
		Long memberId = authService.signUp(request);
		TokenResponse tokenInfo = createTokenService.createTokenInfo(memberId);
		return ApiResponse.success(KAKAO_LOGIN_SUCCESS, tokenInfo);
	}

	@Operation(summary = "카카오 소셜 로그인")
	@PostMapping("/auth/login")
	public ApiResponse<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
		AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
		Long memberId = authService.login(request);
		TokenResponse tokenInfo = createTokenService.createTokenInfo(memberId);
		return ApiResponse.success(KAKAO_LOGIN_SUCCESS, tokenInfo);
	}

	@Operation(summary = "[인증] 로그아웃")
	@Auth
	@PostMapping("/auth/logout")
	public ApiResponse<String> logout(@MemberId Long memberId) {
		commonAuthService.logout(memberId);

		return ApiResponse.success(LOGOUT_SUCCESS);
	}

	@Operation(summary = "JWT 토큰 갱신")
	@PostMapping("/auth/reissue")
	public ApiResponse<TokenResponse> reissue(@Valid @RequestBody TokenRequest request) {
		return ApiResponse.success(JWT_TOKEN_REISSUE_SUCCESS, createTokenService.reissueToken(request));
	}
}
