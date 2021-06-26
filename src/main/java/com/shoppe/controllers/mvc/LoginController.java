package com.shoppe.controllers.mvc;

import com.shoppe.controllers.BaseController;
import com.shoppe.services.LoginService;
import com.shoppe.ui.forms.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add("username");
        mandatoryFields.add("password");
        return mandatoryFields;
    }

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("login")Login login, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            Login response = this.loginService.login(login.getUsername(), login.getPassword());
            if(null != response){
                ModelAndView modelAndView = new ModelAndView("redirect:/admin/home");
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
