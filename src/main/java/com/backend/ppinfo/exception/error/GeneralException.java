package com.backend.ppinfo.exception.error;

import com.backend.ppinfo.exception.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private ErrorCode errorCode;

}
