package com.caliberly.chat.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String error;
    private String message;

}

