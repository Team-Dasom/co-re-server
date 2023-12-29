package com.dongyang.core.api.auth;

import static com.dongyang.core.global.response.SuccessCode.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongyang.core.api.auth.service.AuthService;
import com.dongyang.core.api.auth.service.AuthServiceProvider;
import com.dongyang.core.api.auth.service.CommonAuthService;
import com.dongyang.core.api.auth.service.CreateTokenService;
import com.dongyang.core.api.auth.dto.request.LoginRequest;
import com.dongyang.core.api.auth.dto.request.SignUpRequest;
import com.dongyang.core.api.auth.dto.request.TokenRequest;
import com.dongyang.core.api.auth.dto.response.TokenResponse;
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
    private final CommonAuthService commonAuthService;
    private final CreateTokenService createTokenService;


    @Operation(summary = "OAuth2 소셜 회원가입")
    @PostMapping("/auth/signup")
    public ApiResponse<Void> signUp(@Valid @RequestBody SignUpRequest request, HttpServletResponse response) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        TokenResponse tokenInfo = authService.signUp(request);

        addTokensToCookie(tokenInfo, response);

        return ApiResponse.success(OAUTH_LOGIN_SUCCESS);
    }

    @Operation(summary = "OAuth2 소셜 로그인")
    @PostMapping("/auth/login")
    public ApiResponse<Void> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        TokenResponse tokenInfo = authService.login(request);
        addTokensToCookie(tokenInfo, response);
        
        return ApiResponse.success(OAUTH_LOGIN_SUCCESS);
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
    public ApiResponse<TokenResponse> reissue(@Valid @RequestBody TokenRequest request, HttpServletResponse response) {
        addTokensToCookie(createTokenService.reissueToken(request), response);

        return ApiResponse.success(JWT_TOKEN_REISSUE_SUCCESS);
    }

    private void addTokensToCookie(TokenResponse tokenInfo, HttpServletResponse response) {
        addTokenToCookie("accessToken", tokenInfo.getAccessToken(), response);
        addTokenToCookie("refreshToken", tokenInfo.getRefreshToken(), response);
    }

    private void addTokenToCookie(String cookieName, String token, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, token);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
    }
}
