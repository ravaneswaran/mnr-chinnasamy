package com.shoppe.services;

import com.shoppe.ui.forms.ChangePassword;
import com.shoppe.ui.forms.ForgotPassword;

public interface PasswordService {

    public ForgotPassword forgotPassword(String emailId);

    public ChangePassword changePassword(String emailId, String oldPassword, String newPassword);
}
