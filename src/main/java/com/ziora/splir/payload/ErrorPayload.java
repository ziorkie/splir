package com.ziora.splir.payload;

import java.util.List;

public class ErrorPayload {

    private String message;
    private List<String> details;

    public ErrorPayload(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}