package com.mnrc.core.services;

import com.mnrc.core.forms.LoginForm;

public interface LoginService {

    public LoginForm login(String emailId, String password);

}
