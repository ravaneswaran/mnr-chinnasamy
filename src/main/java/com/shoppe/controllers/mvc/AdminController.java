package com.shoppe.controllers.mvc;

import com.shoppe.controllers.BaseController;
import com.shoppe.services.AdminService;
import com.shoppe.ui.forms.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController extends BaseController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();

        mandatoryFields.add("firstName");
        mandatoryFields.add("emailId");
        mandatoryFields.add("mobileNo");

        return mandatoryFields;
    }

    @GetMapping("/admin")
    public ModelAndView adminHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin-add");
        return modelAndView;
    }

    @PostMapping("/admin/add")
    public ModelAndView addAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if(!bindingResult.hasErrors()){
            Admin response = this.adminService.addAdmin(admin.getFirstName(), admin.getMiddleInitial(), admin.getLastName(), admin.getEmailId(), admin.getUniqueId(), admin.getMobileNo());

            if(null != response) {
                modelAndView.setViewName("admin/admin-info");
                modelAndView.addObject("admin", response);
            } else {
                modelAndView.setViewName("admin/admin-add");
                modelAndView.addObject("admin",admin);
                modelAndView.addObject("errorMessage", "Unable to add admin information...");
            }
        } else {
            modelAndView.setViewName("admin/admin-add");
            modelAndView.addObject("admin", admin);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));
        }

        return modelAndView;
    }

    @GetMapping("/admin/info")
    public ModelAndView getAdminInfo(@RequestParam(name = "uuid") String uuid){
        ModelAndView modelAndView = new ModelAndView();

        if(null != uuid && !"".equals(uuid)){
            Admin admin = this.adminService.getAdmin(uuid);
            modelAndView.setViewName("admin/admin-info");
            modelAndView.addObject("admin", admin);
        } else {
            logger.error("Request parameter uuid is found to be invalid...");
            modelAndView.setViewName("admin/admin-info");
        }

        return modelAndView;
    }

    @GetMapping("/admin/listing")
    public ModelAndView listAdmins(){
        ModelAndView modelAndView = new ModelAndView();

        List<Admin> admins =  this.adminService.listAdmins();
        modelAndView.setViewName("/admin/admin-listing");
        modelAndView.addObject("admins", admins);

        return modelAndView;
    }

    @GetMapping("/admin/block")
    public ModelAndView blockAdmin(@RequestParam(name = "uuid") String uuid){
        ModelAndView modelAndView = new ModelAndView();

        this.adminService.blockAdmin(uuid);

        List<Admin> admins =  this.adminService.listAdmins();
        modelAndView.setViewName("/admin/admin-listing");
        modelAndView.addObject("admins", admins);

        return modelAndView;
    }

    @GetMapping("/admin/unblock")
    public ModelAndView unblockAdmin(@RequestParam(name = "uuid") String uuid){
        ModelAndView modelAndView = new ModelAndView();

        this.adminService.unblockAdmin(uuid);

        List<Admin> admins =  this.adminService.listAdmins();
        modelAndView.setViewName("/admin/admin-listing");
        modelAndView.addObject("admins", admins);

        return modelAndView;
    }

    @GetMapping("/admin/delete")
    public ModelAndView deleteAdmin(@RequestParam(name = "uuid") String uuid){
        ModelAndView modelAndView = new ModelAndView();

        this.adminService.deleteAdmin(uuid);

        List<Admin> admins =  this.adminService.listAdmins();
        modelAndView.setViewName("/admin/admin-listing");
        modelAndView.addObject("admins", admins);

        return modelAndView;
    }
}
