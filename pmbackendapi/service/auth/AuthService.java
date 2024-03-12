package com.miu.pmtbackendapi.service.auth;

import com.miu.pmtbackendapi.domain.auth.dto.request.LoginRequest;
import com.miu.pmtbackendapi.domain.auth.dto.response.LoginResponse;
import com.miu.pmtbackendapi.domain.auth.dto.response.RefreshResponse;
import com.miu.pmtbackendapi.domain.auth.dto.request.RefreshTokenRequest;
import jakarta.servlet.ServletException;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    RefreshResponse refreshTokenAPI() throws ServletException;
}
