package com.mnrc.core.services;

import com.mnrc.core.forms.LoginForm;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    public LoginForm login(String emailId, String password);

}
