package com.mnrc.administration.services;

import com.mnrc.administration.ui.forms.ChangePasswordForm;
import com.mnrc.administration.ui.forms.ForgotPasswordForm;

public interface PasswordService {

    public ForgotPasswordForm forgotPassword(String emailId);

    public ChangePasswordForm changePassword(String emailId, String oldPassword, String newPassword);
}
