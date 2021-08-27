package com.mnrc.administration.controllers.mvc;

import com.mnrc.core.forms.UserForm;
import com.mnrc.core.forms.UserRoleForm;
import com.mnrc.core.services.UserRoleService;
import com.mnrc.core.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MNRCAdministrationUserController extends MNRCAdministrationMvcController {

    Logger logger = LoggerFactory.getLogger(MNRCAdministrationUserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();

        mandatoryFields.add("firstName");
        mandatoryFields.add("emailId");
        mandatoryFields.add("mobileNo");

        return mandatoryFields;
    }

    @GetMapping("/administration/user/home")
    public ModelAndView userHome(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-create");
        modelAndView.addObject("userRoleForms", userRoleForms);
        modelAndView.addObject("selectedUserRoleId", "-1");
        return modelAndView;
    }

    @PostMapping("/administration/user/create")
    public ModelAndView addUser(@Valid @ModelAttribute("admin") UserForm userForm, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if("-1".equals(userForm.getUserRoleId())){
            List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user-create");
            modelAndView.addObject("userForm", userForm);
            modelAndView.addObject("userRoleForms", userRoleForms);
            modelAndView.addObject("selectedUserRoleId", userForm.getUserRoleId());
            modelAndView.addObject("errorMessage", "Please select a valid user role...");

            return modelAndView;
        }

        if(!bindingResult.hasErrors()){
            UserForm response = null;
            try {
                response = this.userService.addUser(userForm.getUserRoleId(), userForm.getFirstName(), userForm.getMiddleInitial(), userForm.getLastName(), userForm.getEmailId(), userForm.getUniqueId(), userForm.getMobileNo());
            } catch (Exception exception) {
                List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user-create");
                modelAndView.addObject("userForm",userForm);
                modelAndView.addObject("userRoleForms", userRoleForms);
                modelAndView.addObject("selectedUserRoleId", userForm.getUserRoleId());
                modelAndView.addObject("errorMessage", exception.getMessage());
                return modelAndView;
            }
            if(null != response) {
                String redirect = String.format("redirect:/administration/user/info?uuid=%s", response.getUserId());
                return new ModelAndView(redirect);
            } else {
                List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user-create");
                modelAndView.addObject("userForm",userForm);
                modelAndView.addObject("userRoleForms", userRoleForms);
                modelAndView.addObject("selectedUserRoleId", userForm.getUserRoleId());
                modelAndView.addObject("errorMessage", "Unable to add admin information...");
                return modelAndView;
            }
        } else {
            List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user-create");
            modelAndView.addObject("userForm", userForm);
            modelAndView.addObject("userRoleForms", userRoleForms);
            modelAndView.addObject("selectedUserRoleId", userForm.getUserRoleId());
            modelAndView.addObject("errorMessage", this.getError(bindingResult));

            return modelAndView;
        }
    }

    @GetMapping("/administration/user/info")
    public ModelAndView getUserInfo(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if(null != uuid && !"".equals(uuid)){
            UserForm userForm = this.userService.getUserForm(uuid);
            if(null != userForm){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user-info");
                modelAndView.addObject("userForm", userForm);
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

    @GetMapping("/administration/user/list")
    public ModelAndView listUsers(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        List<UserForm> userForms =  this.userService.listUsers();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("userForms", userForms);

        return modelAndView;
    }

    @GetMapping("/administration/user/lock")
    public ModelAndView lockUser(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        this.userService.lockUser(uuid);
        List<UserForm> userForms =  this.userService.listUsers();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("userForms", userForms);

        return modelAndView;
    }

    @GetMapping("/administration/user/unlock")
    public ModelAndView unLockUser(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/administration");
        }

        ModelAndView modelAndView = new ModelAndView();
        this.userService.unLockUser(uuid);
        List<UserForm> userForms =  this.userService.listUsers();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("userForms", userForms);

        return modelAndView;
    }

    @GetMapping("/administration/user/delete")
    public ModelAndView deleteUser(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/administration");
        }

        ModelAndView modelAndView = new ModelAndView();
        this.userService.deleteUser(uuid);
        List<UserForm> userForms =  this.userService.listUsers();
        modelAndView.setViewName("user-list");
        modelAndView.addObject("userForms", userForms);

        return modelAndView;
    }
}
