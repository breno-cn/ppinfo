package com.backend.ppinfo.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    GENERAL_ERROR("error.message.general-error"),
    USERNAME_ALREADY_EXISTS("error.message.username-already-exists"),
    EMAIL_ALREADY_EXISTS("error.message.email-already-exists");

    private String errorMessage;

}
