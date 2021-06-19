package com.shoppe.controllers.mvc;

import com.shoppe.controllers.BaseController;
import com.shoppe.enums.UserStatus;
import com.shoppe.services.UserService;
import com.shoppe.ui.forms.AdminForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AdminController extends BaseController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();

        mandatoryFields.add("firstName");
        mandatoryFields.add("emailId");
        mandatoryFields.add("mobileNo");
        mandatoryFields.add("password");
        mandatoryFields.add("confirmPassword");

        return mandatoryFields;
    }

    @GetMapping("/admin")
    public ModelAndView adminHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin-add");
        return modelAndView;
    }

    @PostMapping("/admin/add")
    public ModelAndView addAdmin(@Valid @ModelAttribute("signup") AdminForm adminForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if(!bindingResult.hasErrors()){
            this.userService.addAdmin(adminForm.getFirstName(), adminForm.getMiddleInitial(), adminForm.getLastName(), adminForm.getEmailId(), adminForm.getUniqueId(), adminForm.getMobileNo(), UserStatus.VERIFIED.toString());
            modelAndView.setViewName("admin/admin-add-success");
        } else {
            modelAndView.setViewName("admin/admin-add");
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
        }

        return modelAndView;
    }
}
