package com.shoppe.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public  abstract class BaseController {

    protected abstract List<String> getMandatoryFields();

    protected String getError(BindingResult bindingResult){
        for(String key : getMandatoryFields()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError: fieldErrors) {
                String fieldName = fieldError.getField();
                if(key.equals(fieldName)){
                    return bindingResult.getFieldError(fieldName).getDefaultMessage();
                }
            }
        }
        return null;
    }
}
