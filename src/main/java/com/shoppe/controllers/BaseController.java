package com.shoppe.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {

    protected Map<String, String> getFieldsAndErrors(BindingResult bindingResult){
        Map<String, String> fieldsAndErrors = new HashMap<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError: fieldErrors) {
            String fieldName = fieldError.getField();
            String message = bindingResult.getFieldError(fieldName).getDefaultMessage();
            fieldsAndErrors.put(fieldName, message);
        }
        return fieldsAndErrors;
    }

}
