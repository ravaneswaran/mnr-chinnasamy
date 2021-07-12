package com.mnrc.controllers.mvc;

import com.mnrc.controllers.BaseController;
import com.mnrc.services.EmployeeService;
import com.mnrc.ui.forms.Employee;
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
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    protected List<String> getMandatoryFields() {
        List<String> mandatoryFields = new ArrayList<>();

        mandatoryFields.add("firstName");
        mandatoryFields.add("emailId");
        mandatoryFields.add("mobileNo");

        return mandatoryFields;
    }

    @GetMapping("/home")
    public ModelAndView employeeHome(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee-create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addEmployee(@Valid @ModelAttribute("admin") Employee employee, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if(!bindingResult.hasErrors()){
            Employee response = this.employeeService.addEmployee(employee.getFirstName(), employee.getMiddleInitial(), employee.getLastName(), employee.getEmailId(), employee.getUniqueId(), employee.getMobileNo());
            if(null != response) {
                String redirect = String.format("redirect:/employee/info?uuid=%s", response.getEmployeeId());
                return new ModelAndView(redirect);
            } else {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("employee-create");
                modelAndView.addObject("admin",employee);
                modelAndView.addObject("errorMessage", "Unable to add admin information...");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("employee-create");
            modelAndView.addObject("admin", employee);
            modelAndView.addObject("errorMessage", this.getError(bindingResult));

            return modelAndView;
        }
    }

    @GetMapping("/info")
    public ModelAndView getEmployeeInfo(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        if(null != uuid && !"".equals(uuid)){
            Employee employee = this.employeeService.getEmployee(uuid);
            if(null != employee){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("employee-info");
                modelAndView.addObject("employee", employee);
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("redirect:/404");
                modelAndView.setStatus(HttpStatus.NOT_FOUND);
                return modelAndView;
            }
        } else {
            logger.error("Request parameter uuid is found to be invalid...");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("employee-info");
            return modelAndView;
        }
    }

    @GetMapping("/list")
    public ModelAndView listEmployees(HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        List<Employee> employees =  this.employeeService.listEmployees();
        modelAndView.setViewName("employee-list");
        modelAndView.addObject("employees", employees);

        return modelAndView;
    }

    @GetMapping("/lock")
    public ModelAndView lockEmployee(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        this.employeeService.blockEmployee(uuid);
        List<Employee> employees =  this.employeeService.listEmployees();
        modelAndView.setViewName("employee-list");
        modelAndView.addObject("employees", employees);

        return modelAndView;
    }

    @GetMapping("/unlock")
    public ModelAndView unLockEmployee(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        this.employeeService.unblockEmployee(uuid);
        List<Employee> employees =  this.employeeService.listEmployees();
        modelAndView.setViewName("employee-list");
        modelAndView.addObject("employees", employees);

        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteEmployee(@RequestParam(name = "uuid") String uuid, HttpServletRequest httpServletRequest){
        if(this.isNotUserLoggedIn(httpServletRequest)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        this.employeeService.deleteEmployee(uuid);
        List<Employee> employees =  this.employeeService.listEmployees();
        modelAndView.setViewName("employee-list");
        modelAndView.addObject("employees", employees);

        return modelAndView;
    }
}
