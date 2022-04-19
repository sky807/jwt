package com.jwt.project.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private ErrorCode error;

    public ApiException(ErrorCode e) {
        super(e.getCode());
        this.error = e;
    }
}
