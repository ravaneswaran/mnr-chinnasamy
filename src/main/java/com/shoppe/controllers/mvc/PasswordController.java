package com.shoppe.controllers.mvc;

import com.shoppe.controllers.BaseController;
import com.shoppe.enums.SessionAttribute;
import com.shoppe.services.MailService;
import com.shoppe.services.PasswordService;
import com.shoppe.ui.forms.ChangePassword;
import com.shoppe.ui.forms.ForgotPassword;
import com.shoppe.ui.forms.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        mandatoryFields.add("oldPassword");
        mandatoryFields.add("newPassword");
        mandatoryFields.add("confirmPassword");

        return mandatoryFields;
    }

    @GetMapping("/forgot-password")
    public ModelAndView forgotPasswordHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgot-password");
        return modelAndView;
    }

    @GetMapping("/mail-my-forgotten-password")
    public ModelAndView redirectToForgotPasswordHome(){
        return new ModelAndView("redirect:/forgot-password");
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

    @GetMapping("/change-password")
    public ModelAndView changePasswordHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/change-password");

        return modelAndView;
    }

    @GetMapping("/change-my-password")
    public ModelAndView redirectToChangePasswordHome(){
        return new ModelAndView("redirect:/change-password");
    }

    @PostMapping("/change-my-password")
    public ModelAndView changePassword(@Valid ChangePassword changePassword, BindingResult bindingResult, HttpServletRequest request){

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
            modelAndView.addObject("errorMessage", "New password and confirm password do not match");
            return modelAndView;
        }

        HttpSession httpSession = request.getSession();
        String emailIdInSession = ((Login)httpSession.getAttribute(SessionAttribute.LOGGED_IN_USER.toString())).getEmailId();
        String passwordInSession = ((Login)httpSession.getAttribute(SessionAttribute.LOGGED_IN_USER.toString())).getPassword();

        if(!passwordInSession.equals(changePassword.getOldPassword())) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/change-password");
            modelAndView.addObject("changePassword", changePassword);
            modelAndView.addObject("errorMessage", "Password in session does not match with the old password");
            return modelAndView;
        }

        ChangePassword response = this.passwordService.changePassword(emailIdInSession, changePassword.getOldPassword(), changePassword.getNewPassword());

        if(null != response){
            httpSession.invalidate();
            return new ModelAndView("redirect:/");
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/change-password");
            modelAndView.addObject("changePassword", changePassword);
            modelAndView.addObject("errorMessage", "Sorry!!!... Unable to change the password");
            return modelAndView;
        }
    }
}
