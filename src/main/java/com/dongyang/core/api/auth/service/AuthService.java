package com.dongyang.core.api.auth.service;

import com.dongyang.core.api.auth.dto.request.LoginRequest;
import com.dongyang.core.api.auth.dto.request.SignUpRequest;

public interface AuthService {

	Long signUp(SignUpRequest request);

	Long login(LoginRequest request);
}
