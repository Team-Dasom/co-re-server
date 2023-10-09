package com.dongyang.core.domain.auth.service;

import com.dongyang.core.domain.auth.service.dto.request.LoginRequest;
import com.dongyang.core.domain.auth.service.dto.request.SignUpRequest;

public interface AuthService {

	Long signUp(SignUpRequest request);

	Long login(LoginRequest request);
}
