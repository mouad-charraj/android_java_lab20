package com.example.lab20;

import com.google.gson.annotations.SerializedName;

public class ApiResponse_mouad {

    @SerializedName("success")
    private boolean success_mouad;

    @SerializedName("message")
    private String message_mouad;

    public boolean isSuccess_mouad() {
        return success_mouad;
    }

    public String getMessage_mouad() {
        return message_mouad;
    }
}
