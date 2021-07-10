package com.mnrc.controllers;

import com.mnrc.enums.SessionAttribute;
import com.mnrc.ui.forms.Login;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class BaseController {

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

    protected boolean isUserLoggedIn(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        Login login = (Login)httpSession.getAttribute(SessionAttribute.LOGGED_IN_USER.toString());
        return null != login;
    }

    protected boolean isNotUserLoggedIn(HttpServletRequest httpServletRequest){
        return !this.isUserLoggedIn(httpServletRequest);
    }

    protected String getSessionUserId(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        Login login = (Login)httpSession.getAttribute(SessionAttribute.LOGGED_IN_USER.toString());
        if(null != login){
            return login.getUserId();
        } else {
            return null;
        }
    }

    protected String getSessionUserFullName(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        Login login = (Login)httpSession.getAttribute(SessionAttribute.LOGGED_IN_USER.toString());
        if(null != login){
            return "test test";
        } else {
            return null;
        }
    }
}
