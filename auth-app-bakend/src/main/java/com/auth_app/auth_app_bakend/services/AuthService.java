package com.auth_app.auth_app_bakend.services;

import com.auth_app.auth_app_bakend.dtos.UserDto;

public interface AuthService {

    //register user


    public UserDto registerUser(UserDto userDto);

    //Login User
}
