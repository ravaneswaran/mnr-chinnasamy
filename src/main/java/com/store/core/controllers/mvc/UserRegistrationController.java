package com.store.core.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Controller
public class UserRegistrationController {

    @GetMapping("/sign-up")
    public String signUp(){
        return "signup";
    }

    @PostMapping("/register-user")
    public String registerUser(
            @RequestParam("firstName") @NotBlank @Size(min = 1) String firstName,
            @RequestParam("middleInitial") String middleInitial,
            @RequestParam("lastName") String lastName,
            @RequestParam("emailId") @NotBlank @Size(min = 1) String emailId,
            @RequestParam("uniqueId") String uniqueId,
            @RequestParam("mobileNo") @NotBlank @Size(min = 1) String mobileNo,
            @RequestParam("password") @NotBlank @Size(min = 1) String password,
            @RequestParam("password") @NotBlank @Size(min = 1) String confirmPassword){

        System.out.println("===================================>>>>>>>");

        return "signup";
    }

}
