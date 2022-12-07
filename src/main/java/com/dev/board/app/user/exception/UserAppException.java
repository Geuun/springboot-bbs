package com.dev.board.app.user.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAppException extends RuntimeException{
    private ErrorCode errorCode; // Enum
    private String errorMessage;

    @Override
    public String toString() {
        if (errorMessage == null) return errorCode.getMessage();
        return String.format("%s. %s", errorCode.getMessage(), errorMessage);
    }
}
