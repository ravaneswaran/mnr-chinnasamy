package com.webshoppe.valueobj;

public class SignUpVO {

    private String userUUID;

    private String errorMessage;

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

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
