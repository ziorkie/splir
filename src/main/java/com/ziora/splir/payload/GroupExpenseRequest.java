package com.ziora.splir.payload;

public class GroupExpenseRequest {
    String name;
    Double value;
    Long roomId;

    public GroupExpenseRequest() {
    }

    public GroupExpenseRequest(String name, Double value, Long roomId) {
        this.name = name;
        this.value = value;
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
