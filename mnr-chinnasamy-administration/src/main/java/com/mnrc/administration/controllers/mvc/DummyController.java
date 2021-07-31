package com.mnrc.administration.controllers.mvc;

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

    @GetMapping("/forgot-password-msg")
    public ModelAndView forgotPasswordMsg(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgot-password-msg");
        return modelAndView;
    }

    @GetMapping("/change-password")
    public ModelAndView changePassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("change-password");
        return modelAndView;
    }

    @GetMapping("/500")
    public ModelAndView internalServerError(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/http-errors/500");
        return modelAndView;
    }

    @GetMapping("/404")
    public ModelAndView resourceNotFound(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");
        return modelAndView;
    }

    @GetMapping("/403")
    public ModelAndView forbidden(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("403");
        return modelAndView;
    }

    @GetMapping("/user/role")
    public ModelAndView userRoleHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-role");
        return modelAndView;
    }

    @GetMapping("/payment-gateway")
    public ModelAndView paymentGatewayHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payment-gateway");
        return modelAndView;
    }

}
