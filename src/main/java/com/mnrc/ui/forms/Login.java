package com.mnrc.ui.forms;

import com.mnrc.enums.UserStatus;

import javax.validation.constraints.NotEmpty;

public class Login extends BaseForm {

    @NotEmpty(message = "{login.emailid.not.empty.error.message}")
    private String emailId;

    @NotEmpty(message = "{login.password.not.empty.error.message}")
    private String password;

    private String userId;

    private String status;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isUserBlocked() {
        return UserStatus.BLOCKED.toString().equals(this.status);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
