package com.mnrc.administration.services.vo;

public class BaseVO {

    protected String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isErroneous(){
        return null != this.errorMessage && !this.errorMessage.isEmpty();
    }

    public boolean isNotErroneous(){
        return !isErroneous();
    }

}
