package com.emiteai.willian.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GlobalException extends RuntimeException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public GlobalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public GlobalException(String message) {
        super(message);
    }
}
