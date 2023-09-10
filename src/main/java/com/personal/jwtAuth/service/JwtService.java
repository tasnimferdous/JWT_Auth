package com.personal.jwtAuth.service;

import com.personal.jwtAuth.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JwtService {
    String generateToken(UserDetails userDetails);

    String getUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
