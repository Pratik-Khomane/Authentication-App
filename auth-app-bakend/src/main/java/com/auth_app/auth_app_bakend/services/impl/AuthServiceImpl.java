package com.auth_app.auth_app_bakend.services.impl;

import com.auth_app.auth_app_bakend.dtos.UserDto;
import com.auth_app.auth_app_bakend.services.AuthService;
import com.auth_app.auth_app_bakend.services.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto registerUser(UserDto userDto) {

        //other logic
        //verify email
        //verify password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
         return userService.createUser(userDto);

    }
}
