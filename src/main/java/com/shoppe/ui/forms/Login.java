package com.shoppe.ui.forms;

import javax.validation.constraints.NotEmpty;

public class Login {

    @NotEmpty(message = "${username.not.empty}")
    private String username;

    @NotEmpty(message = "${password.not.empty}")
    private String password;

    private String userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
