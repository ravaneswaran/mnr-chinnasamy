package com.mnrc.administration.services;

import com.mnrc.administration.ui.forms.LoginForm;

public interface LoginService {

    public LoginForm login(String emailId, String password);

}
