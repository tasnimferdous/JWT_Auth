package com.personal.jwtAuth.service.impl;

import com.personal.jwtAuth.dto.JwtAuthResponse;
import com.personal.jwtAuth.dto.RefreshTokenDto;
import com.personal.jwtAuth.dto.SignInDto;
import com.personal.jwtAuth.entity.MyRole;
import com.personal.jwtAuth.entity.User;
import com.personal.jwtAuth.repository.UserRepository;
import com.personal.jwtAuth.service.AuthService;
import com.personal.jwtAuth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public User signUp(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setRole(MyRole.USER);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    public JwtAuthResponse signIn(SignInDto signInDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
        User user = userRepository.findByEmail(signInDto.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid Credential"));
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        jwtAuthResponse.setRefreshToken(refreshToken);

        return jwtAuthResponse;
    }

    @Override
    public JwtAuthResponse renewToken(RefreshTokenDto refreshTokenDto) {
        String email = jwtService.getUserName(refreshTokenDto.getToken());
        User user = userRepository.findByEmail(email).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenDto.getToken(), user)){
            String newToken = jwtService.generateToken(user);
            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setToken(newToken);
            jwtAuthResponse.setRefreshToken(refreshTokenDto.getToken());

            return jwtAuthResponse;
        }
        return null;
    }


}
