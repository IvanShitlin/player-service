package com.intuit.playerservice.exception;

import com.intuit.playerservice.dto.ErrorDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    protected ErrorDto handleException(RuntimeException ex) {
        return new ErrorDto(400, ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PageParameterException.class)
    protected ErrorDto handlePageParameterException(PageParameterException ex) {
        return new ErrorDto(400, ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    protected ErrorDto handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorDto(400, ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    protected ErrorDto handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ErrorDto(404, ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ErrorDto handleException(Exception ex) {
        return new ErrorDto(500, ex.getMessage());
    }
}
