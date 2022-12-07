package com.dev.board.app.user.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response <T> {


    private String resultCode;
    private T result;

    public static Response<Void> error(String resultCode) {
        return new Response(resultCode, null);
    }

    public static  <T> Response success(T result) {
        return new Response("SUCCESS", result);
    }
}
