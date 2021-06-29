package com.shoppe.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        if(null != httpSession){
            httpSession.invalidate();
        }

        return new ModelAndView("redirect:/");
    }
}
