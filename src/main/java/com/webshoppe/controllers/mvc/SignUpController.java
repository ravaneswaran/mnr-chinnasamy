package com.webshoppe.controllers.mvc;

import com.webshoppe.enums.UserStatus;
import com.webshoppe.services.UserService;
import com.webshoppe.services.vo.SignUpVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotEmpty;

@Controller
@Validated
public class SignUpController {

    Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public ModelAndView signUpHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup/signup-home");
        return modelAndView;
    }

    @PostMapping("/signup-user")
    public ModelAndView signUpUser(
            @RequestParam(value = "firstName") @NotEmpty String firstName,
            @RequestParam("middleInitial") String middleInitial,
            @RequestParam("lastName") String lastName,
            @RequestParam("emailId") @NotEmpty String emailId,
            @RequestParam("uniqueId") String uniqueId,
            @RequestParam("mobileNo") @NotEmpty String mobileNo,
            @RequestParam("password") @NotEmpty String password,
            @RequestParam("confirmPassword") @NotEmpty String confirmPassword) {

        ModelAndView modelAndView = new ModelAndView();
        SignUpVO signUpVO  = this.userService.signUp(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, password, confirmPassword, UserStatus.SIGN_UP_VERIFICATION_PENDING.toString());

        if(signUpVO.isNotErroneous()){
            modelAndView.setViewName("signup/signup-success");
        } else {
            modelAndView.addObject("error-message", signUpVO.getErrorMessage());
            modelAndView.setViewName("signup/signup-home");
        }

        return modelAndView;
    }

    @GetMapping("/signup-verification")
    public ModelAndView signUpVerification(@RequestParam() @NotEmpty String signUpVerificationTokenUUID){
        ModelAndView modelAndView = new ModelAndView();
        SignUpVO signUpVO = this.userService.verifySignedUpUser(signUpVerificationTokenUUID);
        if(signUpVO.isNotErroneous()){
            modelAndView.setViewName("login");
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
}
