package com.store.core.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserRegistrationController {

    @PostMapping("/sign-up")
    public String signUp(
            @RequestParam("firstName") String firstName,
            @RequestParam("middleInitial") String middleInitial,
            @RequestParam("lastName") String lastName,
            @RequestParam("emailId") String emailId,
            @RequestParam("uniqueId") String uniqueId,
            @RequestParam("mobileNo") String mobileNo,
            @RequestParam("password") String password){


        return "signup";
    }

}
