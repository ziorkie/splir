package com.ziora.splir.payload;

public class AddUserToRoomRequest {
    private Long userId;
    private Long roomId;

    public AddUserToRoomRequest(Long userId, Long roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

    public AddUserToRoomRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
