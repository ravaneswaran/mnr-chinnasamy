package com.shoppe.ui.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ForgotPassword {

    private String userId;

    @NotEmpty(message = "{admin.email.id.not.empty.error.message}")
    @Email(message = "{admin.email.id.invalid.format.error.message}")
    private String emailId;

    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
