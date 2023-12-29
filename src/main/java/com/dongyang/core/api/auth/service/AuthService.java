package com.dongyang.core.api.auth.service;

import com.dongyang.core.api.auth.dto.request.LoginRequest;
import com.dongyang.core.api.auth.dto.request.SignUpRequest;
import com.dongyang.core.api.auth.dto.response.TokenResponse;

public interface AuthService {

	TokenResponse signUp(SignUpRequest request);

	TokenResponse login(LoginRequest request);
}
