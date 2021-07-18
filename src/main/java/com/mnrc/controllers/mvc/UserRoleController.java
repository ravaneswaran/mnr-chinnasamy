package com.mnrc.controllers.mvc;

import com.mnrc.controllers.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/user")
public class UserRoleController extends BaseController {

    @Override
    protected List<String> getMandatoryFields() {
        return null;
    }

    @GetMapping("/role")
    public ModelAndView userRoleHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-role");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addRole(HttpServletRequest httpServletRequest){
        return null;
    }

}
