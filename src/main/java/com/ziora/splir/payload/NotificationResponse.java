package com.ziora.splir.payload;

public class NotificationResponse {

    Long id;
    String message;
    Long senderId;
    Boolean isSeen;
    String creditorUsername;
    String debtorUsername;


    public NotificationResponse(Long id, String message, Long senderId, Boolean isSeen, String creditorUsername, String debtorUsername) {
        this.id = id;
        this.message = message;
        this.senderId = senderId;
        this.isSeen = isSeen;
        this.creditorUsername = creditorUsername;
        this.debtorUsername = debtorUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public String getCreditorUsername() {
        return creditorUsername;
    }

    public void setCreditorUsername(String creditorUsername) {
        this.creditorUsername = creditorUsername;
    }

    public String getDebtorUsername() {
        return debtorUsername;
    }

    public void setDebtorUsername(String debtorUsername) {
        this.debtorUsername = debtorUsername;
    }
}
