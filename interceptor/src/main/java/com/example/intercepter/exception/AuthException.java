package com.example.intercepter.exception;

public class AuthException extends RuntimeException {

    public AuthException(){
        super("해당 계정은 권한이 없습니다.");
    }
}
