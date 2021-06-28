package com.shoppe.controllers.mvc;

import com.shoppe.controllers.BaseController;
import com.shoppe.services.MailService;
import com.shoppe.services.PasswordService;
import com.shoppe.ui.forms.ForgotPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PasswordController extends BaseController {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private MailService mailService;

    @Value("${forgot.password.mail.subject}")
    private String forgotPasswordMailSubject;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add("emailId");

        return mandatoryFields;
    }

    @GetMapping("/forgot-password")
    public ModelAndView forgotPasswordHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgot-password");

        return modelAndView;
    }

    @PostMapping("/mail-my-forgotten-password")
    public ModelAndView mailForgottenPassword(@Valid ForgotPassword forgotPassword, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            ForgotPassword response = this.passwordService.forgotPassword(forgotPassword.getEmailId());
            if(null != response){
                this.mailService.sendForgotPasswordMail(response.getFirstName(), response.getMiddleInitial(), response.getLastName(), response.getPassword(), response.getEmailId(), this.forgotPasswordMailSubject);
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/forgot-password-msg");
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/forgot-password");
                modelAndView.addObject("forgotPassword", forgotPassword);
                modelAndView.addObject("errorMessage", "Unable to find the email id in the system");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/forgot-password");
            modelAndView.addObject("forgotPassword", forgotPassword);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
            return modelAndView;
        }
    }
}
