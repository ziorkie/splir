package com.ziora.splir.controller;

import com.ziora.splir.model.User;
import com.ziora.splir.payload.UserResponse;
import com.ziora.splir.repository.UserRepository;
import com.ziora.splir.security.CurrentUser;
import com.ziora.splir.security.UserPrincipal;
import com.ziora.splir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser UserPrincipal currentUser){
        UserResponse userResponse= userService.getCurrentUser(currentUser);
        return new ResponseEntity(userResponse, HttpStatus.OK);
    }
}
