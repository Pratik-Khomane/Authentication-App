package com.auth_app.auth_app_bakend.controllers;

import com.auth_app.auth_app_bakend.dtos.UserDto;
import com.auth_app.auth_app_bakend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {


    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(userService.createUser(userDto));
    }
    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(userService.getAllUsers());
    }

    // if {email} and PathVariable String email should be same if its not same then you have to specify in front
    // of pathVariable('email') like this
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserByEmail(email));
    }

    // get user by user Id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable String userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(userId));
    }

    // delete user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    //Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") String userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUser(userDto, userId));
    }
}
