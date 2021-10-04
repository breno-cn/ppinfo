package com.backend.ppinfo.exception.advice;

import com.backend.ppinfo.exception.error.GeneralException;
import com.backend.ppinfo.exception.error.GeneralExceptionMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionAdvice {

    private Environment environment;

    @ResponseBody
    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public GeneralExceptionMessage decodeException(GeneralException exception) {
        var errorCode = exception.getErrorCode();
        var messageKey = errorCode.getErrorMessage();

        return new GeneralExceptionMessage(errorCode, environment.getProperty(messageKey));
    }

}
