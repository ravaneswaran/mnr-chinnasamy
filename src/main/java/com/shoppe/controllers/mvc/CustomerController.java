package com.shoppe.controllers.mvc;

import com.shoppe.controllers.BaseController;
import com.shoppe.services.UserService;
import com.shoppe.services.vo.UserVO;
import com.shoppe.ui.forms.SignUpForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

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

    @GetMapping("/signup")
    public ModelAndView customerSignUpHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup/user/signup-home");
        return modelAndView;
    }

    @PostMapping("/signup/")
    public ModelAndView userSignUp(@Valid @ModelAttribute("signup") SignUpForm signUpForm, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        if(!bindingResult.hasErrors()){
            if(signUpForm.getPassword().equals(signUpForm.getConfirmPassword())) {
                this.userService.signUpUser(signUpForm.getFirstName(), signUpForm.getMiddleInitial(), signUpForm.getLastName(), signUpForm.getEmailId(), signUpForm.getUniqueId(), signUpForm.getMobileNo(), signUpForm.getPassword(), signUpForm.getConfirmPassword());
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