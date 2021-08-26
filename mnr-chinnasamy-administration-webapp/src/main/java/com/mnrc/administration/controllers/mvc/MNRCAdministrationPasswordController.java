package com.mnrc.administration.controllers.mvc;

import com.mnrc.administration.enums.MNRCAdministrationSessionAttribute;
import com.mnrc.core.forms.ChangePasswordForm;
import com.mnrc.core.forms.ForgotPasswordForm;
import com.mnrc.core.forms.LoginForm;
import com.mnrc.core.services.MailService;
import com.mnrc.core.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MNRCAdministrationPasswordController extends MNRCAdministrationMvcController {

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
        mandatoryFields.add("oldPassword");
        mandatoryFields.add("newPassword");
        mandatoryFields.add("confirmPassword");

        return mandatoryFields;
    }

    @GetMapping("/administration/forgot-password")
    public ModelAndView forgotPasswordHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/forgot-password");
        return modelAndView;
    }

    @GetMapping("/administration/mail-my-forgotten-password")
    public ModelAndView redirectToForgotPasswordHome(){
        return new ModelAndView("redirect:/forgot-password");
    }

    @PostMapping("/administration/mail-my-forgotten-password")
    public ModelAndView mailForgottenPassword(@Valid ForgotPasswordForm forgotPassword, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            ForgotPasswordForm response = this.passwordService.forgotPassword(forgotPassword.getEmailId());
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

    @GetMapping("/administration/change-password")
    public ModelAndView changePasswordHome(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/administration");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/change-password");
        return modelAndView;
    }

    @GetMapping("/administration/change-my-password")
    public ModelAndView redirectToChangePasswordHome(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/administration");
        }

        return new ModelAndView("redirect:/administration/change-password");
    }

    @PostMapping("/administration/change-my-password")
    public ModelAndView changePassword(@Valid ChangePasswordForm changePassword, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/change-password");
            modelAndView.addObject("changePassword", changePassword);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
            return modelAndView;
        }

        if(changePassword.getOldPassword().equals(changePassword.getNewPassword())) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/change-password");
            modelAndView.addObject("changePassword", changePassword);
            modelAndView.addObject("errorMessage", "Old password and New Password are same");
            return modelAndView;
        }

        if(!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/change-password");
            modelAndView.addObject("changePassword", changePassword);
            modelAndView.addObject("errorMessage", "New and confirm password do not match");
            return modelAndView;
        }

        HttpSession httpSession = httpServletRequest.getSession();
        String emailIdInSession = ((LoginForm)httpSession.getAttribute(MNRCAdministrationSessionAttribute.LOGGED_IN_USER.toString())).getEmailId();
        String passwordInSession = ((LoginForm)httpSession.getAttribute(MNRCAdministrationSessionAttribute.LOGGED_IN_USER.toString())).getPassword();

        if(!passwordInSession.equals(changePassword.getOldPassword())) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/change-password");
            modelAndView.addObject("changePassword", changePassword);
            modelAndView.addObject("errorMessage", "Password in session does not match with the old password");
            return modelAndView;
        }

        ChangePasswordForm response = this.passwordService.changePassword(emailIdInSession, changePassword.getOldPassword(), changePassword.getNewPassword());

        if(null != response){
            httpSession.invalidate();
            return new ModelAndView("redirect:/administration");
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/change-password");
            modelAndView.addObject("changePassword", changePassword);
            modelAndView.addObject("errorMessage", "Sorry!!!... Unable to change the password");
            return modelAndView;
        }
    }
}
