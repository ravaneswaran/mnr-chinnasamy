package com.shoppe.ui.forms;

import javax.validation.constraints.NotEmpty;

public class SignUpForm extends AdminForm {

    @NotEmpty(message = "{password.not.empty}")
    private String password;

    @NotEmpty(message = "{confirm.password.not.empty}")
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
