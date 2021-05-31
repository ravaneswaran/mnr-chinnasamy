package com.store.core.controllers.mvc;

import com.store.core.enums.UserStatus;
import com.store.core.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Controller
@Validated
public class UserRegistrationController {

    Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/sign-up")
    public String signUp(){
        return "signup";
    }

    @PostMapping("/register-user")
    public String registerUser(
            @RequestParam(value = "firstName") @NotEmpty String firstName,
            @RequestParam("middleInitial") String middleInitial,
            @RequestParam("lastName") String lastName,
            @RequestParam("emailId") @NotEmpty String emailId,
            @RequestParam("uniqueId") String uniqueId,
            @RequestParam("mobileNo") @NotEmpty String mobileNo,
            @RequestParam("password") @NotEmpty String password,
            @RequestParam("confirmPassword") @NotEmpty String confirmPassword) {

        Date date = new Date();
        int serviceStatusCode = this.userService.signUp(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, password, UserStatus.SIGN_UP_VERIFICATION_PENDING.toString(), date, date);

        return 0 == serviceStatusCode ? "signup" : "signup";
    }

}
