package com.mnrc.core.services;

import com.mnrc.core.forms.ChangePasswordForm;
import com.mnrc.core.forms.ForgotPasswordForm;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {

    public ForgotPasswordForm forgotPassword(String emailId);

    public ChangePasswordForm changePassword(String emailId, String oldPassword, String newPassword);
}
