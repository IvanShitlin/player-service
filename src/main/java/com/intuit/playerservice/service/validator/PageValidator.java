package com.intuit.playerservice.service.validator;

import com.intuit.playerservice.exception.PageParameterException;
import org.springframework.stereotype.Component;

@Component
public class PageValidator {

    public void validateRequest(int page, int size) {
        if (page < 0) {
            throw new PageParameterException("Page number cannot be less than zero.");
        }
        if (size < 1) {
            throw new PageParameterException("Page size cannot be less than one.");
        }
    }
}
