package com.backend.ppinfo.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    GENERAL_ERROR("error.message.general-error"),
    USERNAME_ALREADY_EXISTS("error.message.username-already-exists"),
    EMAIL_ALREADY_EXISTS("error.message.email-already-exists"),
    BOARD_NAME_ALREADY_EXISTS("error.message.board-name-already-exists"),
    BOARD_NOT_FOUND("error.message.board-not-found"),
    POST_NOT_FOUND("error.message.post-not-found");

    private String errorMessage;

}
