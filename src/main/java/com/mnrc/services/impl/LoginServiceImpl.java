package com.mnrc.services.impl;

import com.mnrc.models.User;
import com.mnrc.services.LoginService;
import com.mnrc.services.UserService;
import com.mnrc.ui.forms.Login;
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
    public Login login(String emailId, String password) {
        User user = this.userService.getUser(emailId, password);
        if(null != user){
            Login login = new Login();
            login.setEmailId(emailId);
            login.setPassword(password);
            login.setUserId(user.getUUID());
            return login;
        } else {
            logger.error(String.format("UNABLE TO LOGIN : the emailId and password combination does not exist"));
            return null;
        }
    }
}
