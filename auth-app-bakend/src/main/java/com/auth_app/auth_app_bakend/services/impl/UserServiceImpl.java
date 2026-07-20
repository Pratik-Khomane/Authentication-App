package com.auth_app.auth_app_bakend.services.impl;

import com.auth_app.auth_app_bakend.dtos.UserDto;
import com.auth_app.auth_app_bakend.entities.Provider;
import com.auth_app.auth_app_bakend.entities.User;
import com.auth_app.auth_app_bakend.exceptions.ResourceNotFoundException;
import com.auth_app.auth_app_bakend.helpers.UserHelper;
import com.auth_app.auth_app_bakend.repositories.UserRepository;
import com.auth_app.auth_app_bakend.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if(userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("User with given email already exists");
        }

        User user = modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider()!=null ? userDto.getProvider(): Provider.LOCAL);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        UUID uuid = UserHelper.parseUUID(userId);
        User existingUser = userRepository
                .findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(userDto.getName() != null ) existingUser.setName(userDto.getName());

        if(userDto.getImage() != null ) existingUser.setImage(userDto.getImage());

        if(userDto.getProvider() != null) existingUser.setProvider(userDto.getProvider());

        existingUser.setEnabled(userDto.isEnabled());

        //TODO : CHnage password Updation logic
        if(userDto.getPassword() != null) {
            existingUser.setPassword(userDto.getPassword());
        }

        User updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {

        UUID uId = UserHelper.parseUUID(userId);
        User user = userRepository
                .findById(uId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserById(String userId) {

        User user = userRepository
                .findById(UserHelper.parseUUID(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {

       User user = userRepository
               .findByEmail(email)
               .orElseThrow(() -> new ResourceNotFoundException("User not found with given email"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public Iterable<UserDto> getAllUsers() {
        return userRepository.
                findAll().
                stream().
                map( user -> modelMapper.map(user,UserDto.class)).
                toList();
    }
}
