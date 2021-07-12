package com.mnrc.controllers.mvc;

import com.mnrc.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController extends BaseController {

    @Override
    protected List<String> getMandatoryFields() {
        return null;
    }

    @PostMapping("/picture-upload")
    public ModelAndView profilePictureUpload(){
        return null;
    }
}
