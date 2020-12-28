package com.ziora.splir.payload;

public class FriendshipResponse {
    private Long userId;
    private String username;

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
