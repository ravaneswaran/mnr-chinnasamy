package com.shoppe.controllers.mvc;

import com.shoppe.controllers.BaseController;
import com.shoppe.services.MailService;
import com.shoppe.services.PasswordService;
import com.shoppe.ui.forms.ForgotPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PasswordController extends BaseController {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private MailService mailService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add("emailId");

        return mandatoryFields;
    }

    public ModelAndView forgotPassword(ForgotPassword forgotPassword, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            ForgotPassword response = this.passwordService.forgotPassword(forgotPassword.getEmailId());
            if(null != response){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("forgot-password-msg");
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("forgot-password");
                modelAndView.addObject("errorMessage", "Unable to find the email in the system");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("forgot-password");
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
            return modelAndView;
        }
    }


}
