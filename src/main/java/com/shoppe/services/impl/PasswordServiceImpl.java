package com.shoppe.services.impl;

import com.shoppe.models.User;
import com.shoppe.repositories.UserRepository;
import com.shoppe.services.PasswordService;
import com.shoppe.ui.forms.ChangePassword;
import com.shoppe.ui.forms.ForgotPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ForgotPassword forgotPassword(String emailId) {
        User user = this.userRepository.findByEmailId(emailId);
        if(null != user){
            ForgotPassword forgotPassword = new ForgotPassword();
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
    public ChangePassword changePassword(String emailId, String oldPassword, String newPassword) {
        User user = this.userRepository.findByEmailIdAndPassword(emailId, oldPassword);
        if(null != user){
            user.setPassword(newPassword);
            this.userRepository.save(user);

            ChangePassword changePassword = new ChangePassword();
            changePassword.setOldPassword(newPassword);
            changePassword.setNewPassword("");
            changePassword.setConfirmPassword("");

            return changePassword;
        } else {
            return null;
        }
    }
}
