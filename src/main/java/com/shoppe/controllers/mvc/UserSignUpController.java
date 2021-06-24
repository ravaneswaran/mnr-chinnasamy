package com.shoppe.controllers.mvc;

import com.shoppe.controllers.BaseController;
import com.shoppe.enums.UserStatus;
import com.shoppe.enums.UserType;
import com.shoppe.services.UserService;
import com.shoppe.services.vo.UserVO;
import com.shoppe.ui.forms.SignUpForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserSignUpController extends BaseController {

    Logger logger = LoggerFactory.getLogger(UserSignUpController.class);

    @Autowired
    private UserService userService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();

        mandatoryFields.add("firstName");
        mandatoryFields.add("emailId");
        mandatoryFields.add("mobileNo");
        mandatoryFields.add("password");
        mandatoryFields.add("confirmPassword");

        return mandatoryFields;
    }

    @GetMapping("/signup/user")
    public ModelAndView userSignUpHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup/user/signup-home");
        return modelAndView;
    }

    @PostMapping("/signup/user/add")
    public ModelAndView userSignUp(@Valid @ModelAttribute("signup") SignUpForm signUpForm, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        if(!bindingResult.hasErrors()){
            if(signUpForm.getPassword().equals(signUpForm.getConfirmPassword())) {
                this.userService.signUp(signUpForm.getFirstName(), signUpForm.getMiddleInitial(), signUpForm.getLastName(), signUpForm.getEmailId(), signUpForm.getUniqueId(), signUpForm.getMobileNo(), signUpForm.getPassword(), signUpForm.getConfirmPassword(), UserType.CUSTOMER.toString(), UserStatus.SIGN_UP_VERIFICATION_PENDING.toString());
                modelAndView.setViewName("signup/user/signup-success");
            } else {
                modelAndView.setViewName("signup/user/signup-home");
                modelAndView.addObject("errorMessage", "Password and Confirm password should be same");
            }
        } else {
            modelAndView.setViewName("signup/user/signup-home");
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
        }

        return modelAndView;
    }

    @GetMapping("/signup/user/verification")
    public ModelAndView userSignUpVerification(@RequestParam(name = "token") @NotEmpty String signUpVerificationTokenUUID){
        ModelAndView modelAndView = new ModelAndView();
        UserVO userVO = this.userService.verifySignedUpUser(signUpVerificationTokenUUID);
        if(userVO.isNotErroneous()){
            modelAndView.setViewName("login");
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
}