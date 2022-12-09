package com.dev.board.app.user.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    ;

    private String role;
}

