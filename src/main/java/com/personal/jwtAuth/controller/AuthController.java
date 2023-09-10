package com.personal.jwtAuth.controller;

import com.personal.jwtAuth.dto.JwtAuthResponse;
import com.personal.jwtAuth.dto.RefreshTokenDto;
import com.personal.jwtAuth.dto.SignInDto;
import com.personal.jwtAuth.entity.User;
import com.personal.jwtAuth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user){
        return authService.signUp(user);
    }
    @PostMapping("/signIn")
    public JwtAuthResponse signUp(@RequestBody SignInDto signInDto){
        return authService.signIn(signInDto);
    }
    @PostMapping("/renew")
    public JwtAuthResponse renewToken(@RequestBody RefreshTokenDto refreshTokenDto){
        return authService.renewToken(refreshTokenDto);
    }
}
