package com.webshoppe.controllers.mvc;

import com.webshoppe.enums.UserStatus;
import com.webshoppe.enums.UserType;
import com.webshoppe.models.Token;
import com.webshoppe.services.TokenService;
import com.webshoppe.services.UserService;
import com.webshoppe.utils.MailerUtil;
import com.webshoppe.utils.StringUtil;
import com.webshoppe.valueobj.SignUpVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.Date;

@Controller
@Validated
public class UserRegistrationController {

    Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MailerUtil mailerUtil;

    @Autowired
    private StringUtil stringUtil;

    @GetMapping("/sign-up")
    public String signUpHome(){
        return "signup";
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
        SignUpVO signUpVO  = this.userService.signUp(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, password, UserStatus.SIGN_UP_VERIFICATION_PENDING.toString(), date, date);

        if(null != signUpVO){
            Token token = this.tokenService.storeAndGetSignUpVerificationToken(signUpVO.getUserUUID(), UserType.CUSTOMER.toString());
            try {
                String mailContent = this.stringUtil.getResourceAsString("mail-templates/signup-verification-mail.html");
                mailContent = String.format(mailContent, firstName, middleInitial, lastName, token.getUUID());
                try {
                    this.mailerUtil.sendMailMessage("noreply@test.com", emailId, "Signup Verification Mail", mailContent);
                    return "signup";
                } catch (MessagingException e) {
                    this.logger.error(e.getMessage(), e);
                }
            } catch (IOException e) {
                this.logger.error(e.getMessage(), e);
            }
        } else {
            return "signup";
        }

        return "signup";
    }

}
