package com.shoppe.services;

import com.shoppe.ui.forms.ForgotPassword;

public interface PasswordService {

    public ForgotPassword forgotPassword(String emailId);

}
