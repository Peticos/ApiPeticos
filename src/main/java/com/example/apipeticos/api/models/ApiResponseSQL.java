package com.example.apipeticos.api.models;

public class ApiResponseSQL {

    private String message;

    public ApiResponseSQL(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
