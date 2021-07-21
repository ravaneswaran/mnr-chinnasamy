package com.mnrc.administration.controllers.mvc;

import com.mnrc.administration.services.UserService;
import com.mnrc.administration.ui.forms.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();

        mandatoryFields.add("firstName");
        mandatoryFields.add("emailId");
        mandatoryFields.add("mobileNo");

        return mandatoryFields;
    }

    @GetMapping("/home")
    public ModelAndView userHome(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addUser(@Valid @ModelAttribute("admin") UserForm userForm, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if(!bindingResult.hasErrors()){
            UserForm response = this.userService.addUser(userForm.getFirstName(), userForm.getMiddleInitial(), userForm.getLastName(), userForm.getEmailId(), userForm.getUniqueId(), userForm.getMobileNo());
            if(null != response) {
                String redirect = String.format("redirect:/user/info?uuid=%s", response.getUserId());
                return new ModelAndView(redirect);
            } else {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user-create");
                modelAndView.addObject("userForm",userForm);
                modelAndView.addObject("errorMessage", "Unable to add admin information...");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user-create");
            modelAndView.addObject("userForm", userForm);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));

            return modelAndView;
        }
    }

    @GetMapping("/info")
    public ModelAndView getUserInfo(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if(null != uuid && !"".equals(uuid)){
            UserForm userForm = this.userService.getUserForm(uuid);
            if(null != userForm){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user-info");
                modelAndView.addObject("user-form", userForm);
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("redirect:/404");
                modelAndView.setStatus(HttpStatus.NOT_FOUND);
                return modelAndView;
            }
        } else {
            logger.error("Request parameter uuid is found to be invalid...");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user-info");
            return modelAndView;
        }
    }

    @GetMapping("/list")
    public ModelAndView listUsers(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        List<UserForm> userForms =  this.userService.listUsers();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("user-forms", userForms);

        return modelAndView;
    }

    @GetMapping("/lock")
    public ModelAndView lockUser(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        this.userService.lockUser(uuid);
        List<UserForm> userForms =  this.userService.listUsers();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("user-forms", userForms);

        return modelAndView;
    }

    @GetMapping("/unlock")
    public ModelAndView unLockUser(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        this.userService.unLockUser(uuid);
        List<UserForm> userForms =  this.userService.listUsers();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("user-forms", userForms);

        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteUser(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        this.userService.deleteUser(uuid);
        List<UserForm> userForms =  this.userService.listUsers();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("user-forms", userForms);

        return modelAndView;
    }
}
