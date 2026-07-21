package com.auth_app.auth_app_bakend.controllers;

import com.auth_app.auth_app_bakend.dtos.UserDto;
import com.auth_app.auth_app_bakend.services.AuthService;
import com.auth_app.auth_app_bakend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.registerUser(userDto));

    }
}
