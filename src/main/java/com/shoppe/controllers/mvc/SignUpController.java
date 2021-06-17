package com.shoppe.controllers.mvc;

import com.shoppe.controllers.BaseController;
import com.shoppe.enums.UserStatus;
import com.shoppe.services.UserService;
import com.shoppe.services.vo.SignUpVO;
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
import java.util.Map;

@Controller
public class SignUpController extends BaseController {

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
    public ModelAndView signUpUser(@Valid @ModelAttribute("signup")SignUpForm signUpForm, BindingResult result) {

        ModelAndView modelAndView = new ModelAndView();
        SignUpVO signUpVO  = this.userService.signUp(signUpForm.getFirstName(), signUpForm.getMiddleInitial(), signUpForm.getLastName(), signUpForm.getEmailId(), signUpForm.getUniqueId(), signUpForm.getMobileNo(), signUpForm.getPassword(), signUpForm.getConfirmPassword(), UserStatus.SIGN_UP_VERIFICATION_PENDING.toString());

        if(result.hasErrors()){
            modelAndView.setViewName("signup/signup-home");
            Map<String, String> fieldsAndErrors = this.getFieldsAndErrors(result);
            modelAndView.addObject("errorMessage", fieldsAndErrors.get("firstName"));
        } else {
            modelAndView.setViewName("signup/signup-success");
        }

        return modelAndView;
    }

    @GetMapping("/signup-verification")
    public ModelAndView signUpVerification(@RequestParam(name = "token") @NotEmpty String signUpVerificationTokenUUID){
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
