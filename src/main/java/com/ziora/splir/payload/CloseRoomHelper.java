package com.ziora.splir.payload;

public class CloseRoomHelper {
    Long userId;
    Double value;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public CloseRoomHelper() {
    }

    public CloseRoomHelper(Long userId, Double value) {
        this.userId = userId;
        this.value = value;
    }
}
