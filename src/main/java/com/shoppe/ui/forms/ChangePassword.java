package com.shoppe.ui.forms;

import javax.validation.constraints.NotEmpty;

public class ChangePassword extends BaseForm{

    private String userId;

    private String emailId;

    @NotEmpty(message = "{change.password.oldpassword.empty.error.message}")
    private String oldPassword;

    @NotEmpty(message = "{change.password.newpassword.empty.error.message}")
    private String newPassword;

    @NotEmpty(message = "{change.password.confirmpassword.empty.error.message}")
    private String confirmPassword;

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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
