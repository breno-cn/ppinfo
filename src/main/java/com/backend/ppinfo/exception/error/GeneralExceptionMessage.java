package com.backend.ppinfo.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralExceptionMessage {

    private ErrorCode errorCode;
    private String message;

}
