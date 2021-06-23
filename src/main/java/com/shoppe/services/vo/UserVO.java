package com.shoppe.services.vo;

public class UserVO extends BaseVO{

    private String userUUID;

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public boolean isErroneous(){
        return !isNotErroneous();
    }
}