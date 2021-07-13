package com.mnrc.ui.forms;

import com.mnrc.enums.UserStatus;

import javax.validation.constraints.NotEmpty;

public class Login extends BaseForm {

    private String firstName;

    private String middleInitial;

    private String lastName;

    @NotEmpty(message = "{login.emailid.not.empty.error.message}")
    private String emailId;

    @NotEmpty(message = "{login.password.not.empty.error.message}")
    private String password;

    private String userId;

    private String status;

    private String type;

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
        return UserStatus.LOCKED.toString().equals(this.status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
