package com.mnrc.services;

import com.mnrc.ui.forms.ChangePassword;
import com.mnrc.ui.forms.ForgotPassword;

public interface PasswordService {

    public ForgotPassword forgotPassword(String emailId);

    public ChangePassword changePassword(String emailId, String oldPassword, String newPassword);
}
