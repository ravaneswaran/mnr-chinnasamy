package com.mnrc.core.services.impl;

import com.mnrc.core.entities.User;
import com.mnrc.core.services.LoginService;
import com.mnrc.core.services.UserService;
import com.mnrc.core.forms.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
            login.setRoleId(user.getRole().getUUID());
            login.setRoleName(user.getRole().getName());
            login.setCanAccessAdministrationApp(1 == user.getRole().getCanAccessAdministrationApp());

            return login;
        } else {
            logger.error(String.format("UNABLE TO LOGIN : the emailId and password combination does not exist"));
            return null;
        }
    }
}
