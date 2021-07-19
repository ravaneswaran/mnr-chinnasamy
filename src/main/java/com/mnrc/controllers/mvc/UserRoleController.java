package com.mnrc.controllers.mvc;

import com.mnrc.controllers.BaseController;
import com.mnrc.enums.SessionAttribute;
import com.mnrc.services.UserRoleService;
import com.mnrc.ui.forms.Login;
import com.mnrc.ui.forms.UserRoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/role")
public class UserRoleController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();
        mandatoryFields.add("userRoleName");
        return mandatoryFields;
    }

    @GetMapping("/view")
    public ModelAndView userRoleHome(){
        List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user-role");
        modelAndView.addObject("userroleforms", userRoleForms);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView returnToUserRoleHome(){
        return userRoleHome();
    }

    @PostMapping("/add")
    public ModelAndView addRole(@Valid UserRoleForm userRoleForm, BindingResult bindingResult, HttpServletRequest httpServletRequest){

        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();

        if(!bindingResult.hasErrors()){
            Login login = (Login) httpServletRequest.getSession().getAttribute(SessionAttribute.LOGGED_IN_USER.toString());
            try {
                this.userRoleService.addUserRole(userRoleForm.getUserRoleName(), String.format("%s, %s", login.getFirstName(), login.getLastName()));
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/user-role");
                modelAndView.addObject("userrole", userRoleForm);
                modelAndView.addObject("userroleforms", userRoleForms);
                return modelAndView;
            } catch (Exception exception) {
                if(DataIntegrityViolationException.class.equals(exception.getClass())){
                    ModelAndView modelAndView = new ModelAndView();
                    modelAndView.setViewName("/user-role");
                    modelAndView.addObject("userrole", userRoleForm);
                    modelAndView.addObject("userroleforms", userRoleForms);
                    modelAndView.addObject("errorMessage", "User role already exists...");
                    return modelAndView;
                } else {
                    ModelAndView modelAndView = new ModelAndView();
                    modelAndView.setViewName("/user-role");
                    modelAndView.addObject("userrole", userRoleForm);
                    modelAndView.addObject("userroleforms", userRoleForms);
                    modelAndView.addObject("errorMessage", exception.getMessage());
                    return modelAndView;
                }
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/user-role");
            modelAndView.addObject("userrole", userRoleForm);
            modelAndView.addObject("userroleforms", userRoleForms);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView editRole(@Valid UserRoleForm userRoleForm, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if(!bindingResult.hasErrors()){
            Login login = (Login) httpServletRequest.getSession().getAttribute(SessionAttribute.LOGGED_IN_USER.toString());
            try {
                UserRoleForm response = this.userRoleService.editUserRole(userRoleForm.getUserRoleId(), userRoleForm.getUserRoleName(), String.format("%s, %s", login.getFirstName(), login.getLastName()));
                List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/user-role");
                modelAndView.addObject("userrole", userRoleForm);
                modelAndView.addObject("userroleforms", userRoleForms);

                return modelAndView;

            } catch (Exception e) {
                List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/user-role");
                modelAndView.addObject("userrole", userRoleForm);
                modelAndView.addObject("userroleforms", userRoleForms);
                modelAndView.addObject("errorMessage", e.getMessage());
                return modelAndView;
            }
        } else {
            List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/user-role");
            modelAndView.addObject("userrole", userRoleForm);
            modelAndView.addObject("userroleforms", userRoleForms);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
            return modelAndView;
        }
    }

    @GetMapping("/edit")
    public ModelAndView editRole(@RequestParam(name = "uuid") String userRoleId, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        List<UserRoleForm> userRoleForms = this.userRoleService.getUserRoles();

        if(null == userRoleId || "".equals(userRoleId)){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/user-role");
            modelAndView.addObject("userroleforms", userRoleForms);
            modelAndView.addObject("errorMessage", "Invalid uuid parameter...");

            return modelAndView;
        }

        try {
            UserRoleForm userRoleForm = this.userRoleService.getUserRole(userRoleId);
            userRoleForm.setAction("/user/role/edit");

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/user-role");
            modelAndView.addObject("userrole", userRoleForm);
            modelAndView.addObject("userroleforms", userRoleForms);

            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/user-role");
            modelAndView.addObject("userroleforms", userRoleForms);
            modelAndView.addObject("errorMessage", e.getMessage());

            return modelAndView;
        }
    }
}
