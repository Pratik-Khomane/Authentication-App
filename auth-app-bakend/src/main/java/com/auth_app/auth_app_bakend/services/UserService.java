package com.auth_app.auth_app_bakend.services;

import com.auth_app.auth_app_bakend.dtos.UserDto;

import java.util.UUID;

public interface UserService {

    // Create User
    UserDto createUser(UserDto userDto);

    //update user
    UserDto updateUser(UserDto userDto, String userId);

    void deleteUser(String userId);

    UserDto getUserById(String userId);

    UserDto getUserByEmail(String email);

    Iterable<UserDto> getAllUsers();

}
