package com.mnrc.administration.services.impl;

import com.mnrc.administration.models.User;
import com.mnrc.administration.services.LoginService;
import com.mnrc.administration.services.UserService;
import com.mnrc.administration.ui.forms.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public LoginForm login(String emailId, String password) {
        User user = this.userService.getUser(emailId, password);
        if(null != user){
            LoginForm login = new LoginForm();

            login.setFirstName(user.getFirstName());
            login.setMiddleInitial(user.getMiddleInitial());
            login.setLastName(user.getLastName());
            login.setEmailId(emailId);
            login.setPassword(password);
            login.setUserId(user.getUUID());
            login.setStatus(user.getStatus());
            login.setType(user.getType());

            return login;
        } else {
            logger.error(String.format("UNABLE TO LOGIN : the emailId and password combination does not exist"));
            return null;
        }
    }
}
