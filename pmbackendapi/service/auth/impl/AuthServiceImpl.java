package com.miu.pmtbackendapi.service.auth.impl;

import com.miu.pmtbackendapi.domain.auth.dto.request.LoginRequest;
import com.miu.pmtbackendapi.domain.auth.dto.request.RefreshTokenRequest;
import com.miu.pmtbackendapi.domain.auth.dto.response.LoginResponse;
import com.miu.pmtbackendapi.domain.auth.dto.response.RefreshResponse;
import com.miu.pmtbackendapi.exception.customexception.UserDeactivedException;
import com.miu.pmtbackendapi.service.auth.AuthService;
import com.miu.pmtbackendapi.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException(ex.getMessage());
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }

        authentication.getPrincipal();
        final CustomUserDetails userDetails =  userDetailsService.loadUserByUsername(authentication.getName());
        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        final Long uId = userDetails.getId();
        var loginResponse = new LoginResponse(accessToken, refreshToken, uId);

        return loginResponse;

    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());

        if (isRefreshTokenValid) {
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if (isAccessTokenExpired) {
                System.out.println("Access token is expired.");
            } else {
                System.out.println("ACCESS TOKEN IS STILL VALID - NOT EXPIRED.");
            }

            final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getAccessToken()));
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken(), 0L);

            return loginResponse;
        }
        return new LoginResponse();
    }

    @Override
    public RefreshResponse refreshTokenAPI() throws ServletException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    String refreshToken = cookie.getValue();
                    boolean isRefreshTokenValid = jwtUtil.validateToken(refreshToken);

                    if (isRefreshTokenValid) {
                        var accessToken = jwtUtil.generateRefreshToken(jwtUtil.getSubject(refreshToken));
                        System.out.println("***************************************");
                        System.out.println("TOKEN REFRESHED as =>" + refreshToken);
                        System.out.println("***************************************");

                        SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(accessToken));
                        return new RefreshResponse(accessToken, refreshToken);
                    }
                }
            }
        }
        return new RefreshResponse();
    }
}
