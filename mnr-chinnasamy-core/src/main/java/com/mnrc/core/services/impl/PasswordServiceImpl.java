package com.mnrc.core.services.impl;

import com.mnrc.core.entities.User;
import com.mnrc.core.repositories.UserRepository;
import com.mnrc.core.services.PasswordService;
import com.mnrc.core.forms.ChangePasswordForm;
import com.mnrc.core.forms.ForgotPasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ForgotPasswordForm forgotPassword(String emailId) {
        User user = this.userRepository.findByEmailId(emailId);
        if(null != user){
            ForgotPasswordForm forgotPassword = new ForgotPasswordForm();
            forgotPassword.setUserId(user.getUUID());
            forgotPassword.setEmailId(user.getEmailId());
            forgotPassword.setPassword(user.getPassword());
            forgotPassword.setFirstName(user.getFirstName());
            forgotPassword.setMiddleInitial(user.getMiddleInitial());
            forgotPassword.setLastName(user.getLastName());

            return forgotPassword;
        } else {
            return null;
        }
    }

    @Override
    public ChangePasswordForm changePassword(String emailId, String oldPassword, String newPassword) {
        User user = this.userRepository.findByEmailIdAndPassword(emailId, oldPassword);
        if(null != user){
            user.setPassword(newPassword);
            this.userRepository.save(user);

            ChangePasswordForm changePassword = new ChangePasswordForm();
            changePassword.setOldPassword(newPassword);
            changePassword.setNewPassword("");
            changePassword.setConfirmPassword("");

            return changePassword;
        } else {
            return null;
        }
    }
}
