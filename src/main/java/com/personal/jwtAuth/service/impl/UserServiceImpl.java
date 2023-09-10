package com.personal.jwtAuth.service.impl;

import com.personal.jwtAuth.repository.UserRepository;
import com.personal.jwtAuth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                return userRepository.findByEmail(userName)
                        .orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        };
    }
}
