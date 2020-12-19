package com.ziora.splir.payload;

import com.ziora.splir.model.Invitation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class InvitedUserResponse {

    private Long userId;
    private String username;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
