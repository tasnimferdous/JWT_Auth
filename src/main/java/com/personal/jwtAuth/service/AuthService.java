package com.personal.jwtAuth.service;

import com.personal.jwtAuth.dto.JwtAuthResponse;
import com.personal.jwtAuth.dto.RefreshTokenDto;
import com.personal.jwtAuth.dto.SignInDto;
import com.personal.jwtAuth.entity.User;

public interface AuthService {
    User signUp(User user);
    JwtAuthResponse signIn(SignInDto signInDto);
    JwtAuthResponse renewToken(RefreshTokenDto refreshTokenDto);
}
