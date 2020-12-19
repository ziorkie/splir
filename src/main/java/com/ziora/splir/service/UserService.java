package com.ziora.splir.service;

import com.ziora.splir.payload.UserResponse;
import com.ziora.splir.security.CurrentUser;
import com.ziora.splir.security.UserPrincipal;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserResponse getCurrentUser(UserPrincipal userPrincipal){
        UserResponse userResponse = new UserResponse(userPrincipal.getId(), userPrincipal.getUsername(), userPrincipal.getName());

        return userResponse;
    }
}
