package com.api.service.reporting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends Exceptions {

    public InternalErrorException(String message,HttpStatus code) {
        super(message,code);
    }

}