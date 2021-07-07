package com.mnrc.controllers.mvc;

import com.mnrc.controllers.BaseController;
import com.mnrc.enums.SessionAttribute;
import com.mnrc.services.LoginService;
import com.mnrc.ui.forms.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    private String redirectUrlIfLoggedIn = "redirect:/employee/home";

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add("emailId");
        mandatoryFields.add("password");
        return mandatoryFields;
    }

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest httpServletRequest){
        if(this.isUserLoggedIn(httpServletRequest)) {
            return new ModelAndView(this.redirectUrlIfLoggedIn);
        }
        return new ModelAndView("/login");
    }

    @GetMapping("/login")
    public ModelAndView redirectToLoginHome(HttpServletRequest httpServletRequest){
        if(this.isUserLoggedIn(httpServletRequest)) {
            return new ModelAndView(this.redirectUrlIfLoggedIn);
        }
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("login")Login login, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        if(this.isUserLoggedIn(httpServletRequest)) {
            return new ModelAndView(this.redirectUrlIfLoggedIn);
        }

        if(!bindingResult.hasErrors()){
            Login response = this.loginService.login(login.getEmailId(), login.getPassword());
            if(null != response){
                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setAttribute(SessionAttribute.LOGGED_IN_USER.toString(), response);
                ModelAndView modelAndView = new ModelAndView("redirect:/employee/home");
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/login");
                modelAndView.addObject("login", login);
                modelAndView.addObject("errorMessage", "Email id and password combination does not exist");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/login");
            modelAndView.addObject("login", login);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
            return modelAndView;
        }
    }
}
