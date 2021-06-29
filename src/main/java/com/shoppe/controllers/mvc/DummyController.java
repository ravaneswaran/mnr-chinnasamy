package com.shoppe.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dummy")
public class DummyController {

    @GetMapping("/admin/info")
    public ModelAndView getAdminInfoPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/admin-info");
        return modelAndView;
    }

    @GetMapping("/admin/listing")
    public ModelAndView getAdminListingPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/admin-listing");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/forgot-password")
    public ModelAndView forgotPassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgot-password");
        return modelAndView;
    }

}
