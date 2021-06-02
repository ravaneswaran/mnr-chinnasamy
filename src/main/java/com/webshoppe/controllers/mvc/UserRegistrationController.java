package com.webshoppe.controllers.mvc;

import com.webshoppe.enums.UserStatus;
import com.webshoppe.services.UserService;
import com.webshoppe.valueobj.SignUpVO;
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
    public String signUpHome(){
        return "registration/sign-up";
    }

    @PostMapping("/sign-up-user")
    public String signUpUser(
            @RequestParam(value = "firstName") @NotEmpty String firstName,
            @RequestParam("middleInitial") String middleInitial,
            @RequestParam("lastName") String lastName,
            @RequestParam("emailId") @NotEmpty String emailId,
            @RequestParam("uniqueId") String uniqueId,
            @RequestParam("mobileNo") @NotEmpty String mobileNo,
            @RequestParam("password") @NotEmpty String password,
            @RequestParam("confirmPassword") @NotEmpty String confirmPassword) {

        Date date = new Date();
        SignUpVO signUpVO  = this.userService.signUp(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, password, confirmPassword, UserStatus.SIGN_UP_VERIFICATION_PENDING.toString(), date, date);

        if(signUpVO.isNotErroneous()){
            return "registration/sign-up-success";
        } else {
            return "registration/sign-up";
        }
    }

    @GetMapping("/sign-up-verification")
    public String signUpVerification(@RequestParam() @NotEmpty String signUpVerificationTokenUUID){
        SignUpVO signUpVO = this.userService.verifySignedUpUser(signUpVerificationTokenUUID);
        if(signUpVO.isNotErroneous()){
            return "login";
        } else {
            return "error";
        }
    }
}
